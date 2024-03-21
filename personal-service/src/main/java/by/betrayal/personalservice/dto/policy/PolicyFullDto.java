package by.betrayal.personalservice.dto.policy;

import by.betrayal.personalservice.dto.person.PersonFullDto;
import lombok.Data;

import java.util.Date;

@Data
public class PolicyFullDto {

    private Long id;

    private String number;

    private Date dateEnd;

    private PersonFullDto patient;
}
