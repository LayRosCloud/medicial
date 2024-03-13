package by.betrayal.personalservice.dto.contact;

import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import lombok.Data;

@Data
public class ContactFullDto {

    private Long id;

    private String value;

    private PersonFullDto patient;

    private ContactTypeFullDto type;
}
