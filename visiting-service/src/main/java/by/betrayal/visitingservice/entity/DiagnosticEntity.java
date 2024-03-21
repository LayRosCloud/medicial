package by.betrayal.visitingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "diagnostics")
public class DiagnosticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "date", nullable = false)
    private Long date;

    @Column(name = "is_healed", nullable = false)
    private Boolean IsHealed;
}
