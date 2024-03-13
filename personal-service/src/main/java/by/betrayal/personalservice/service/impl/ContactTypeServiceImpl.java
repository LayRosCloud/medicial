package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.contact.type.ContactTypeCreateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeUpdateDto;
import by.betrayal.personalservice.entity.ContactTypeEntity;
import by.betrayal.personalservice.mapper.ContactTypeMapper;
import by.betrayal.personalservice.repository.ContactTypeRepository;
import by.betrayal.personalservice.service.ContactTypeService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository repository;
    private final ContactTypeMapper mapper;

    @Override
    @Transactional
    public List<ContactTypeFullDto> findAll() {
        var list = repository.findAll();

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public ContactTypeFullDto findById(Short id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public ContactTypeFullDto create(ContactTypeCreateDto dto) {
        var type = mapper.toEntity(dto);

        var result = repository.saveAndFlush(type);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public ContactTypeFullDto update(ContactTypeUpdateDto dto) {
        var type = findByIdOrThrowNotFoundException(dto.getId());

        mapper.toEntity(type, dto);

        var result =  repository.saveAndFlush(type);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public ContactTypeFullDto delete(Short id) {
        var type = findByIdOrThrowNotFoundException(id);

        repository.delete(type);

        return mapper.toFullDto(type);
    }

    private ContactTypeEntity findByIdOrThrowNotFoundException(Short id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
