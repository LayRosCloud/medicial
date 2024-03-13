package by.betrayal.personalservice.dto.address;

import by.betrayal.personalservice.dto.person.PersonFullDto;
import lombok.Data;

@Data
public class AddressFullDto {

    private Long id;

    private String zipCode;

    private String country;

    private String region;

    private String city;

    private String street;

    private String apartment;

    private PersonFullDto patient;

}
