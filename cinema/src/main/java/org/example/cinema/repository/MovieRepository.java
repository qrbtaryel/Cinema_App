package org.example.cinema.repository;

import org.example.cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m from Movie m where " +
//            "(:name is null or m.name ilike '%:name%') and " +
//            "(:description is null or m.movieDescription ilike '%:description%') and " +
            "(:imdbRate is null or m.imdbRate=:imdbRate) and " +
            "(:realiseYear is null or m.realiseYear=:realiseYear)")
    Page<Movie> findAll(@Param("name") String name, @Param("description") String description,
                        @Param("imdbRate") Double imdbRate, @Param("realiseYear") Integer realiseYear, Pageable pageable);
}
