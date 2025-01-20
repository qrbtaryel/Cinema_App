package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.response.TicketResponse;
import org.example.cinema.dto.response.UserResponse;
import org.example.cinema.enums.Status;
import org.example.cinema.model.MovieSession;
import org.example.cinema.model.Ticket;
import org.example.cinema.model.User;
import org.example.cinema.repository.TicketRepository;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.TicketService;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.cinema.config.SecurityUtil.getCurrentUserId;
import static org.example.cinema.enums.Status.ACTIVE;
import static org.example.cinema.enums.Status.SOLD;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final MovieService movieService;
    private static final BigDecimal price = BigDecimal.valueOf(10);
    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Override
    @Transactional
    public List<TicketResponse> getTickets(Long movieSessionId) {
        var movieSession = movieService.findMovieSessionById(movieSessionId);

        return ticketRepository.findAllByMovieSession(movieSession).stream()
                .map(this::convertToTicketResponse).toList();
    }

    @Override
    @Transactional
    public List<TicketResponse> getTickets() {
        Long currentUserId = getCurrentUserId();
        return ticketRepository.findAllByUserId(currentUserId).stream().map(this::convertToTicketResponse).toList();
    }

    @Override
    @Transactional
    public void createTickets(Long movieSessionId) {
        var movieSession = movieService.findMovieSessionById(movieSessionId);

        if (!ticketRepository.existsAllByMovieSession(movieSession)) {
            Double surchargeRate = movieSession.getSession().getSurchargeRate();
            var ticketPrice = price.add(price.multiply(BigDecimal.valueOf(surchargeRate)).divide(BigDecimal.valueOf(100)));

            List<Ticket> tickets = movieSession.getHall().getSeats().stream()
                    .map(seat -> Ticket.builder()
                            .status(Status.ACTIVE)
                            .movieSession(movieSession)
                            .seat(seat)
                            .price(ticketPrice)
                            .build())
                    .collect(Collectors.toList());
            ticketRepository.saveAll(tickets);
        }
    }

    @Override
    @Transactional
    public TicketResponse buyTicket(Long id) {
        User currentUser = userService.findCurrentUser();
        Ticket ticket = find(id);
        if (SOLD.equals(ticket.getStatus())) {
            throw new ResponseStatusException(BAD_REQUEST, "Bilet artıq satılıb.");
        }
        ticket.setStatus(SOLD);
        ticket.setSoldTime(LocalDateTime.now());
        ticket.setUser(currentUser);
        ticketRepository.save(ticket);
        userService.reduceBalance(currentUser, ticket.getPrice());

        return convertToTicketResponse(ticket);
    }

    @Override
    @Transactional
    public void returnTicket(Long id) {
        User currentUser = userService.findCurrentUser();
        Ticket ticket = find(id);
        if (!(SOLD.equals(ticket.getStatus()) && currentUser.equals(ticket.getUser()))) {
            throw new ResponseStatusException(BAD_REQUEST, "Bilet satılmayıb.");
        }
        ticket.setStatus(ACTIVE);
        ticket.setSoldTime(null);
        ticket.setUser(null);
        ticketRepository.save(ticket);
        userService.increaseBalance(currentUser, ticket.getPrice());
    }

    private TicketResponse convertToTicketResponse(Ticket ticket) {
        MovieSession movieSession = ticket.getMovieSession();
        String movieName = movieSession.getMovie().getName();
        String sessionName = movieSession.getSession().getName();
        boolean sold = SOLD.equals(ticket.getStatus());
        UserResponse userResponse = userService.convertUserResponse(ticket.getUser());

        return TicketResponse.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .sold(sold)
                .soldTime(ticket.getSoldTime())
                .sessionName(sessionName)
                .movieName(movieName)
                .user(userResponse)
                .seatNumber(ticket.getSeat().getSeatNumber())
                .hallName(ticket.getSeat().getHall().getName())
                .build();
    }

    private Ticket find(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Bilet tapılmadı"));
    }
}
