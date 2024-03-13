package by.betrayal.personalservice.dto.address;

import lombok.Data;

@Data
public class AddressCreateDto {


    private String zipCode;

    private String country;

    private String region;

    private String city;

    private String street;

    private String apartment;

    private Long patientId;
}
