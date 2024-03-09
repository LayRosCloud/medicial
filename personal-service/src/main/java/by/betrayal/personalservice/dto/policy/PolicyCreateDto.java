package by.betrayal.personalservice.dto.policy;

import lombok.Data;

import java.util.Date;

@Data
public class PolicyCreateDto {

    private String number;

    private Date dateEnd;

    private Long patientId;
}
