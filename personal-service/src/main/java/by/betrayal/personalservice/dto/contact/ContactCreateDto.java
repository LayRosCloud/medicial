package by.betrayal.personalservice.dto.contact;

import lombok.Data;

@Data
public class ContactCreateDto {

    private Long patientId;

    private String value;

    private Short typeId;
}
