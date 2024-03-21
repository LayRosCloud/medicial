package by.betrayal.personalservice.core.utils.creator;

import by.betrayal.personalservice.core.utils.FakerUtils;
import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.entity.PersonEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonCreationUtils {

    public static List<PersonEntity> generatePersons(int count) {
        var persons = new ArrayList<PersonEntity>();

        for (int i = 0; i < count; i++) {
            persons.add(generatePerson());
        }

        return persons;
    }

    public static PersonEntity generatePerson() {
        var faker = FakerUtils.FAKER;

        var item = PersonEntity.builder()
                .birthday(faker.date().birthday())
                .sex(faker.bool().bool())
                .image(UUID.randomUUID().toString() + ".jpg")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .patronymic(faker.name().suffix())
                .build();

        return item;
    }

    public static PersonEntity generatePersonWithId() {
        var faker = FakerUtils.FAKER;

        var item = generatePerson();
        item.setId(faker.number().randomNumber());

        return item;
    }

    public static PersonCreateDto generateCreateDto() {
        var faker = FakerUtils.FAKER;

        var item = new PersonCreateDto();
        item.setFirstName(faker.name().firstName());
        item.setLastName(faker.name().lastName());
        item.setPatronymic(faker.name().name());
        // var image = new MockMultipartFile(UUID.randomUUID().toString(), new byte[10]);
        item.setImage(null);

        item.setSex(faker.bool().bool());
        item.setBirthday(faker.date().birthday());

        return item;
    }

    public static PersonCreateDto generateCreateInvalidDto() {

        var item = generateCreateDto();
        item.setFirstName("");

        return item;
    }

    public static PersonUpdateDto generateUpdateDto(Long id) {
        var faker = FakerUtils.FAKER;

        var item = new PersonUpdateDto();
        item.setId(id);
        item.setFirstName(faker.name().firstName());
        item.setLastName(faker.name().lastName());
        item.setPatronymic(faker.name().suffix());
        // var image = new MockMultipartFile(UUID.randomUUID().toString(), new byte[10]);
        item.setImage(null);

        item.setSex(faker.bool().bool());
        item.setBirthday(faker.date().birthday());

        return item;
    }

    public static PersonUpdateDto generateUpdateInvalidIdDto() {
        var item = generateUpdateDto(20000L);

        return item;
    }

    public static PersonUpdateDto generateUpdateInvalidDto(Long id) {
        var item = generateUpdateDto(20000L);
        item.setFirstName("");

        return item;
    }
}
