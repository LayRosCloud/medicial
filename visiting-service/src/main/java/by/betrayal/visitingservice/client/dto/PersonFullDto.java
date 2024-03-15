package by.betrayal.visitingservice.client.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonFullDto {
    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Boolean sex;

    private Date birthday;

    private String image;
}
