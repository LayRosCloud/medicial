package by.betrayal.visitingservice.dto.visit;

import by.betrayal.visitingservice.entity.InstitutionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitCreateDto {
    private Long patientId;

    private Long date;

    private Boolean isAppoint;

    private Short institutionId;
}
