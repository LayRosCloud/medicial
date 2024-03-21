package by.betrayal.personalservice.dto.medicalcard;

import lombok.Data;

import java.util.Date;

@Data
public class MedicalCardUpdateDto {

    private Long id;

    private String number;

    private Date date;

    private Long patientId;
}
