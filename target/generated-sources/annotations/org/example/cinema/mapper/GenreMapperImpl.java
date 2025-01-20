package org.example.cinema.mapper;

import javax.annotation.processing.Generated;
import org.example.cinema.dto.request.GenreRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.model.Genre;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T18:00:41+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre toGenre(GenreRequest genreRequest) {
        if ( genreRequest == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setName( genreRequest.getName() );

        return genre;
    }

    @Override
    public GenreResponse toGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse genreResponse = new GenreResponse();

        genreResponse.setId( genre.getId() );
        genreResponse.setName( genre.getName() );

        return genreResponse;
    }
}
