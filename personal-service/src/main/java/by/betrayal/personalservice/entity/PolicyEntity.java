package by.betrayal.personalservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "policies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PersonEntity patient;

    @Column(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Long patientId;

}
