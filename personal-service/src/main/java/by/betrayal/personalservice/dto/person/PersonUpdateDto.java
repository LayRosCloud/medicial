package by.betrayal.personalservice.dto.person;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Data
public class PersonUpdateDto {

    @Min(value = 1, message = "id min 1")
    private Long id;

    @Size(min = 3, max = 40, message = "lastName must be 3 between 40 symbols")
    private String lastName;

    @Size(min = 3, max = 40, message = "firstName must be 3 between 40 symbols")
    private String firstName;

    @Size(min = 0, max = 40, message = "patronymic must be 0 between 40 symbols")
    private String patronymic;

    @NotNull(message = "sex is not null")
    private Boolean sex;

    @NotNull(message = "birthday is not null")
    private Instant birthday;

    private MultipartFile image;
}
