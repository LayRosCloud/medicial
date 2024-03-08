package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
}
