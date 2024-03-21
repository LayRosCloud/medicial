package by.betrayal.personalservice.mapper;

import by.betrayal.personalservice.dto.address.AddressCreateDto;
import by.betrayal.personalservice.dto.address.AddressFullDto;
import by.betrayal.personalservice.dto.address.AddressUpdateDto;
import by.betrayal.personalservice.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface AddressMapper {

    List<AddressFullDto> toFullDto(List<AddressEntity> items);

    AddressFullDto toFullDto(AddressEntity item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    AddressEntity toEntity(AddressCreateDto dto);

    default void toEntity(AddressEntity item, AddressUpdateDto dto) {
        item.setZipCode(dto.getZipCode());
        item.setCountry(dto.getCountry());
        item.setRegion(dto.getRegion());
        item.setCity(dto.getCity());
        item.setStreet(dto.getStreet());
        item.setApartment(dto.getApartment());
    }

}
