package by.betrayal.personalservice.dto.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;


@Data
public class PersonCreateDto {

    @Size(min = 3, max = 40, message = "lastName must be 3 between 40 symbols")
    @NotBlank(message = "lastName doesnt symbols")
    private String lastName;

    @Size(min = 3, max = 40, message = "firstName must be 3 between 40 symbols")
    @NotBlank(message = "firstName doesnt symbols")
    private String firstName;

    @Size(min = 0, max = 40, message = "patronymic must be 0 between 40 symbols")
    @NotBlank(message = "patronymic doesnt symbols")
    private String patronymic;

    @NotNull(message = "sex is not null")
    private Boolean sex;

    @NotNull(message = "birthday is not null")
    private Date birthday;

    private MultipartFile image;
}
