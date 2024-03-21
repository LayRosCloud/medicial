package by.betrayal.visitingservice.mapper;

import by.betrayal.visitingservice.dto.visit.VisitCreateDto;
import by.betrayal.visitingservice.dto.visit.VisitFullDto;
import by.betrayal.visitingservice.dto.visit.VisitUpdateDto;
import by.betrayal.visitingservice.entity.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = InstitutionMapper.class)
public interface VisitMapper {
    List<VisitFullDto> toFullDto(List<VisitEntity> items);
    VisitFullDto toFullDto(VisitEntity item);
    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "id", ignore = true)
    VisitEntity toEntity(VisitCreateDto dto);

    default void toEntity(VisitEntity item, VisitUpdateDto dto) {
        if(!item.equals(dto.getId()) ) {
            throw new RuntimeException();
        }

        item.setDate(dto.getDate());
        item.setIsAppoint(dto.getIsAppoint());
        item.setPatientId(dto.getPatientId());
    }
}
