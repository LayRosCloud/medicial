package by.betrayal.personalservice.dto.medicalcard;

import by.betrayal.personalservice.dto.person.PersonFullDto;
import lombok.Data;

import java.util.Date;

@Data
public class MedicalCardFullDto {

    private Long id;

    private String number;

    private Date date;

    private PersonFullDto patient;
}
