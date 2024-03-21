package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactFullDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;
import by.betrayal.personalservice.entity.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, ContactTypeMapper.class})
public interface ContactMapper {

    ContactFullDto toFullDto(ContactEntity item);

    List<ContactFullDto> toFullDto(List<ContactEntity> items);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "type", ignore = true)
    ContactEntity toEntity(ContactCreateDto dto);

    default void toEntity(ContactEntity item, ContactUpdateDto dto) {
        item.setValue(dto.getValue());
    }
}
