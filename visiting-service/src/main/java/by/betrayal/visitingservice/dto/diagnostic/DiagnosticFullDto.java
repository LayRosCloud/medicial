package by.betrayal.visitingservice.dto.diagnostic;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticFullDto {
    private Long id;

    private Long patientId;

    private String value;

    private Long date;

    private Boolean IsHealed;
}
