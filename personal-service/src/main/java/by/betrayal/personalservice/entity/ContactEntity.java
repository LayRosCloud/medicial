package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ContactTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PersonEntity patient;

    @Column(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Long patientId;
}
