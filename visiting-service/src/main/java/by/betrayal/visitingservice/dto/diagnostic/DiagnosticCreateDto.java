package by.betrayal.visitingservice.dto.diagnostic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticCreateDto {
    private Long patientId;

    private String value;

    private Long date;

    private Boolean IsHealed;
}
