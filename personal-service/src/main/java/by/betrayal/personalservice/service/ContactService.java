package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactFullDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;

import java.util.List;

public interface ContactService {

    List<ContactFullDto> findAll();

    List<ContactFullDto> findAll(Long patientId);
    ContactFullDto findById(Long id);
    ContactFullDto create(ContactCreateDto dto);

    ContactFullDto update(ContactUpdateDto dto);

    ContactFullDto delete(Long id);

}
