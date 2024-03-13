package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.medicalcard.MedicalCardCreateDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardFullDto;
import by.betrayal.personalservice.dto.medicalcard.MedicalCardUpdateDto;
import by.betrayal.personalservice.entity.MedicalCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface MedicalCardMapper {

    MedicalCardFullDto toFullDto(MedicalCardEntity item);
    List<MedicalCardFullDto> toFullDto(List<MedicalCardEntity> item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    MedicalCardEntity toEntity(MedicalCardCreateDto dto);

    default void toEntity(MedicalCardEntity item, MedicalCardUpdateDto dto) {
        item.setDate(dto.getDate());
        item.setNumber(dto.getNumber());
    }
}
