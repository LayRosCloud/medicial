package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonFullDto toFullDto(PersonEntity item);

    List<PersonFullDto> toFullDto(List<PersonEntity> items);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    PersonEntity toEntity(PersonCreateDto dto);

    default void toEntity(PersonEntity entity, PersonUpdateDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setSex(dto.getSex());
        entity.setBirthday(dto.getBirthday());
    }
}
