package by.betrayal.visitingservice.dto.diagnostic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticUpdateDto {
    private Long id;

    private Long patientId;

    private String value;

    private Long date;

    private Boolean IsHealed;
}
