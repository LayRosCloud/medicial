package by.betrayal.visitingservice.dto.institution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionFullDto {

    private Short id;

    private String name;
}
