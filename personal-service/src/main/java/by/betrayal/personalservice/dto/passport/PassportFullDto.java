package by.betrayal.personalservice.dto.passport;

import by.betrayal.personalservice.dto.person.PersonFullDto;
import lombok.Data;

@Data
public class PassportFullDto {

    private Long id;

    private String series;

    private String number;

    private PersonFullDto patient;
}
