package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passports")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PersonEntity patient;

    @Column(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Long patientId;
}
