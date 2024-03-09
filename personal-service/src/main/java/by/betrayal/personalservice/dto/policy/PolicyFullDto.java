package by.betrayal.personalservice.dto.policy;

import by.betrayal.personalservice.entity.PersonEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PolicyFullDto {

    private Long id;

    private String number;

    private Date dateEnd;

    private PersonEntity patient;
}
