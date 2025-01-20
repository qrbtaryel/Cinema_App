package org.example.cinema.service;

import org.example.cinema.dto.request.TicketRequest;
import org.example.cinema.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {
    List<TicketResponse> getTickets(Long movieSessionId);
    List<TicketResponse> getTickets();

    TicketResponse buyTicket(Long id);

    void createTickets(Long movieSessionId);

    void returnTicket(Long id);
}
