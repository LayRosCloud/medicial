package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.address.AddressCreateDto;
import by.betrayal.personalservice.dto.address.AddressFullDto;
import by.betrayal.personalservice.dto.address.AddressUpdateDto;

import java.util.List;

public interface AddressService {
    List<AddressFullDto> findAll();
    List<AddressFullDto> findAll(Long patientId);
    AddressFullDto findById(Long id);
    AddressFullDto create(AddressCreateDto dto);
    AddressFullDto update(AddressUpdateDto dto);
    AddressFullDto delete(Long id);
}
