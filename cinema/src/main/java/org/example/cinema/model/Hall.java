package org.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "halls")
public class Hall {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Hall_name", nullable = false)
    @Comment(value = "Zalin adi")
    private String name;

    @Column(name = "max_seat_capacity")
    @Comment("max oturacaq sayı")
    private Integer maxSeatCapacity;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    @JsonManagedReference
    private List<Seat> seats = new ArrayList<>();

//   @OneToMany(fetch = FetchType.LAZY, mappedBy = "hall", cascade = CascadeType.MERGE)
//   private List<Session> session;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(id, hall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
