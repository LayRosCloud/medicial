package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "patronymic", nullable = false)
    public String patronymic;

    @Column(name = "sex", nullable = false)
    public Boolean sex;

    @Column(name = "birthday", nullable = false)
    public Date birthday;

    @Column(name = "image")
    public String image;
}
