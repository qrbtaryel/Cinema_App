package org.example.cinema.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.cinema.dto.request.MovieRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.dto.response.HallResponse;
import org.example.cinema.dto.response.LanguageResponse;
import org.example.cinema.dto.response.MovieResponse;
import org.example.cinema.dto.response.MovieSessionResponse;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.model.Genre;
import org.example.cinema.model.Hall;
import org.example.cinema.model.Language;
import org.example.cinema.model.Movie;
import org.example.cinema.model.MovieSession;
import org.example.cinema.model.Session;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T18:00:41+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie toEntity(MovieRequest request) {
        if ( request == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.name( request.getName() );
        movie.duration( request.getDuration() );
        movie.author( request.getAuthor() );
        movie.movieDescription( request.getMovieDescription() );
        movie.userAge( request.getUserAge() );
        movie.imdbRate( request.getImdbRate() );
        movie.realiseYear( request.getRealiseYear() );
        movie.status( request.getStatus() );

        return movie.build();
    }

    @Override
    public MovieSessionResponse toMovieSessionResponse(MovieSession movieSession) {
        if ( movieSession == null ) {
            return null;
        }

        MovieSessionResponse movieSessionResponse = new MovieSessionResponse();

        movieSessionResponse.setId( movieSession.getId() );
        movieSessionResponse.setDate( movieSession.getDate() );
        movieSessionResponse.setHall( hallToHallResponse( movieSession.getHall() ) );
        movieSessionResponse.setSession( sessionToSessionResponse( movieSession.getSession() ) );
        movieSessionResponse.setLanguage( languageToLanguageResponse( movieSession.getLanguage() ) );

        return movieSessionResponse;
    }

    @Override
    public MovieResponse toResponse(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieResponse movieResponse = new MovieResponse();

        movieResponse.setMovieSessions( movieSessionListToMovieSessionResponseList( movie.getSessions() ) );
        movieResponse.setId( movie.getId() );
        movieResponse.setName( movie.getName() );
        movieResponse.setMovieDescription( movie.getMovieDescription() );
        movieResponse.setUserAge( movie.getUserAge() );
        movieResponse.setImdbRate( movie.getImdbRate() );
        movieResponse.setRealiseYear( movie.getRealiseYear() );
        movieResponse.setStatus( movie.getStatus() );
        movieResponse.setGenres( genreListToGenreResponseList( movie.getGenres() ) );
        movieResponse.setAuthor( movie.getAuthor() );
        movieResponse.setDuration( movie.getDuration() );

        return movieResponse;
    }

    @Override
    public void update(Movie movie, MovieRequest movieRequest) {
        if ( movieRequest == null ) {
            return;
        }

        movie.setName( movieRequest.getName() );
        movie.setDuration( movieRequest.getDuration() );
        movie.setAuthor( movieRequest.getAuthor() );
        movie.setMovieDescription( movieRequest.getMovieDescription() );
        movie.setUserAge( movieRequest.getUserAge() );
        movie.setImdbRate( movieRequest.getImdbRate() );
        movie.setRealiseYear( movieRequest.getRealiseYear() );
        movie.setStatus( movieRequest.getStatus() );
    }

    protected HallResponse hallToHallResponse(Hall hall) {
        if ( hall == null ) {
            return null;
        }

        HallResponse.HallResponseBuilder hallResponse = HallResponse.builder();

        hallResponse.id( hall.getId() );
        hallResponse.name( hall.getName() );

        return hallResponse.build();
    }

    protected SessionResponse sessionToSessionResponse(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionResponse.SessionResponseBuilder sessionResponse = SessionResponse.builder();

        sessionResponse.id( session.getId() );
        sessionResponse.name( session.getName() );
        sessionResponse.surchargeRate( session.getSurchargeRate() );

        return sessionResponse.build();
    }

    protected LanguageResponse languageToLanguageResponse(Language language) {
        if ( language == null ) {
            return null;
        }

        LanguageResponse.LanguageResponseBuilder languageResponse = LanguageResponse.builder();

        languageResponse.id( language.getId() );
        languageResponse.name( language.getName() );

        return languageResponse.build();
    }

    protected List<MovieSessionResponse> movieSessionListToMovieSessionResponseList(List<MovieSession> list) {
        if ( list == null ) {
            return null;
        }

        List<MovieSessionResponse> list1 = new ArrayList<MovieSessionResponse>( list.size() );
        for ( MovieSession movieSession : list ) {
            list1.add( toMovieSessionResponse( movieSession ) );
        }

        return list1;
    }

    protected GenreResponse genreToGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse genreResponse = new GenreResponse();

        genreResponse.setId( genre.getId() );
        genreResponse.setName( genre.getName() );

        return genreResponse;
    }

    protected List<GenreResponse> genreListToGenreResponseList(List<Genre> list) {
        if ( list == null ) {
            return null;
        }

        List<GenreResponse> list1 = new ArrayList<GenreResponse>( list.size() );
        for ( Genre genre : list ) {
            list1.add( genreToGenreResponse( genre ) );
        }

        return list1;
    }
}
