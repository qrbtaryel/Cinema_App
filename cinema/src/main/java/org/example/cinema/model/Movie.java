package org.example.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinema.enums.Status;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment(value = "Filmin adi")
    private String name;

    @Column(name = "duration_in_min")
    @Comment("filmin müddəti")
    private Integer duration;

    @Column(name = "author")
    @Comment(value = "Filmin rejissoru")
    private String author;

    @Column(name = "movie_description", nullable = false, length = 1000)
    @Comment(value = "Filmin icmali")
    private String movieDescription;

    @Column(name = "user_age")
    @Comment(value = "Film hansi yas araligi ucundur")
    private Integer userAge;

    @Column(name = "IMDB_rate", nullable = false)
    @Comment(value = "Filmin IMDB reytinqi")
    private Double imdbRate;

    @Column(name = "realise_year", nullable = false)
    @Comment(value = "Filmin cixma tarixi")
    private Integer realiseYear;

    @Column(name = "status", nullable = false)
    @Comment(value = "Filmin statusu")
    private Status status;

    @Builder.Default
    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    @Comment(value = "Filmin janri")
    private List<Genre> genres = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = LAZY, mappedBy = "movie")
    private List<MovieSession> sessions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", userAge=" + userAge +
                ", imdbRate=" + imdbRate +
                ", realiseYear=" + realiseYear +
                '}';
    }
}
