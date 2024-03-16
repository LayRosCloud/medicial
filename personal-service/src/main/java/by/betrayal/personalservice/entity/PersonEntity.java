package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "people")
public class PersonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "image")
    private String image;
}
