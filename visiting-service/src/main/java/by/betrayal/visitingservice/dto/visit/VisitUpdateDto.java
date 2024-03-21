package by.betrayal.visitingservice.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitUpdateDto {
    private Long id;

    private Long patientId;

    private Long date;

    private Boolean isAppoint;

    private Short institutionId;
}
