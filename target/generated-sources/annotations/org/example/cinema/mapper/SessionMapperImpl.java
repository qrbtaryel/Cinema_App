package org.example.cinema.mapper;

import javax.annotation.processing.Generated;
import org.example.cinema.dto.request.SessionRequest;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.model.Session;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T18:00:41+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class SessionMapperImpl implements SessionMapper {

    @Override
    public Session toSession(SessionRequest sessionRequest) {
        if ( sessionRequest == null ) {
            return null;
        }

        Session.SessionBuilder session = Session.builder();

        session.name( sessionRequest.getName() );
        session.surchargeRate( sessionRequest.getSurchargeRate() );

        return session.build();
    }

    @Override
    public SessionResponse toSessionResponse(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionResponse.SessionResponseBuilder sessionResponse = SessionResponse.builder();

        sessionResponse.id( session.getId() );
        sessionResponse.name( session.getName() );
        sessionResponse.surchargeRate( session.getSurchargeRate() );

        return sessionResponse.build();
    }
}
