package by.betrayal.personalservice.dto.passport;

import lombok.Data;

@Data
public class PassportUpdateDto {

    private Long id;

    private String series;

    private String number;

    private Long patientId;
}
