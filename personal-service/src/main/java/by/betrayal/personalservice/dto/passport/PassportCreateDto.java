package by.betrayal.personalservice.dto.passport;

import lombok.Data;

@Data
public class PassportCreateDto {

    private String series;

    private String number;

    private Long patientId;
}
