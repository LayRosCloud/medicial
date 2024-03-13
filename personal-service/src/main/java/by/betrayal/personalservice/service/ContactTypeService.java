package by.betrayal.personalservice.service;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeCreateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeUpdateDto;

import java.util.List;

public interface ContactTypeService {
    List<ContactTypeFullDto> findAll();
    ContactTypeFullDto findById(Short id);
    ContactTypeFullDto create(ContactTypeCreateDto dto);
    ContactTypeFullDto update(ContactTypeUpdateDto dto);
    ContactTypeFullDto delete(Short id);
}
