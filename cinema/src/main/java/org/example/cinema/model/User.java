package org.example.cinema.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false)
    @Comment(value = "istifadecinin soyadi")
    private String surname;

    @Column(name = "name", nullable = false)
    @Comment(value = "istifadecinin adi")
    private String name;

    @Column(name = "patronymic", nullable = false)
    @Comment(value = "ata adi")
    private String patronymic;

    @Column(name = "username", nullable = false)
    @Comment(value = "Istifadeci adi")
    private String username;

    @Column(name = "password", nullable = false)
    @Comment(value = "istifadeci passwordu")
    private String password;

    @Column(name = "balance", nullable = false)
    @Comment(value = "istifadecinin balansi")
    private BigDecimal balance;

    @Column(name = "birthdate", nullable = false)
    @Comment(value = "istifadecinin dogum tarixi")
    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", birthdate=" + birthdate +
                '}';
    }
}
