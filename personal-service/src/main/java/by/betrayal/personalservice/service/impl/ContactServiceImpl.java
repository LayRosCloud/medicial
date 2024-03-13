package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactFullDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;
import by.betrayal.personalservice.entity.ContactEntity;
import by.betrayal.personalservice.entity.ContactTypeEntity;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.mapper.ContactMapper;
import by.betrayal.personalservice.repository.ContactRepository;
import by.betrayal.personalservice.repository.ContactTypeRepository;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.ContactService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactTypeRepository typeRepository;
    private final PersonRepository personRepository;
    private final ContactMapper mapper;


    @Override
    @Transactional
    public List<ContactFullDto> findAll() {
        var list = contactRepository.findAll();

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public List<ContactFullDto> findAll(Long patientId) {
        var list = contactRepository.findAllByPatientId(patientId);

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public ContactFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public ContactFullDto create(ContactCreateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());
        var type = findByIdTypeOrThrowNotFoundException(dto.getTypeId());

        var contact = mapper.toEntity(dto);
        contact.setType(type);
        contact.setPatient(patient);

        var result = contactRepository.saveAndFlush(contact);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public ContactFullDto update(ContactUpdateDto dto) {
        var contact = findByIdOrThrowNotFoundException(dto.getId());
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());
        var type = findByIdTypeOrThrowNotFoundException(dto.getTypeId());

        mapper.toEntity(contact, dto);
        contact.setType(type);
        contact.setPatient(patient);

        var result = contactRepository.saveAndFlush(contact);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public ContactFullDto delete(Long id) {
        var contact = findByIdOrThrowNotFoundException(id);

        contactRepository.delete(contact);

        return mapper.toFullDto(contact);
    }

    private PersonEntity findByIdPersonOrThrowNotFoundException(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private ContactTypeEntity findByIdTypeOrThrowNotFoundException(Short id) {
        return typeRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private ContactEntity findByIdOrThrowNotFoundException(Long id) {
        return contactRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
