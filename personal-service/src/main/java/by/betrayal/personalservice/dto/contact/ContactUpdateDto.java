package by.betrayal.personalservice.dto.contact;

import lombok.Data;

@Data
public class ContactUpdateDto {

    private Long id;

    private Long patientId;

    private String value;

    private Short typeId;
}
