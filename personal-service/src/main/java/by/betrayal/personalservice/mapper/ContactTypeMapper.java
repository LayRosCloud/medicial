package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.contact.type.ContactTypeCreateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeUpdateDto;
import by.betrayal.personalservice.entity.ContactTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactTypeMapper {
    ContactTypeFullDto toFullDto(ContactTypeEntity item);
    List<ContactTypeFullDto> toFullDto(List<ContactTypeEntity> item);

    @Mapping(target = "id", ignore = true)
    ContactTypeEntity toEntity(ContactTypeCreateDto dto);

    default void toEntity(ContactTypeEntity item, ContactTypeUpdateDto dto) {
        item.setName(dto.getName());
    }
}
