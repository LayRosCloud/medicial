package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.passport.PassportCreateDto;
import by.betrayal.personalservice.dto.passport.PassportFullDto;
import by.betrayal.personalservice.dto.passport.PassportUpdateDto;
import by.betrayal.personalservice.entity.PassportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassportMapper {

    PassportFullDto toFullDto(PassportEntity item);

    List<PassportFullDto> toFullDto(List<PassportEntity> items);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    PassportEntity toEntity(PassportCreateDto dto);

    default void toEntity(PassportEntity item, PassportUpdateDto dto) {
        item.setSeries(dto.getSeries());
        item.setNumber(dto.getNumber());
    }

}
