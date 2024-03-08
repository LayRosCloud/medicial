package by.betrayal.personalservice.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
public class PersonFullDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Boolean sex;

    private Instant birthday;

    private String image;
}
