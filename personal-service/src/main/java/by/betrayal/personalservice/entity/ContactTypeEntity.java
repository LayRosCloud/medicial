package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_types")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name", nullable = false)
    private String name;
}
