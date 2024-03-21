package by.betrayal.visitingservice.dto.visit;

import by.betrayal.visitingservice.dto.institution.InstitutionFullDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitFullDto {
    private Long id;

    private Long patientId;

    private Long date;

    private Boolean isAppoint;

    private InstitutionFullDto institution;
}
