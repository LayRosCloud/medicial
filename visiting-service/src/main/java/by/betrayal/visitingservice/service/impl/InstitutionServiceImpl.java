package by.betrayal.visitingservice.service.impl;

import by.betrayal.visitingservice.dto.institution.InstitutionCreateDto;
import by.betrayal.visitingservice.dto.institution.InstitutionFullDto;
import by.betrayal.visitingservice.dto.institution.InstitutionUpdateDto;
import by.betrayal.visitingservice.entity.InstitutionEntity;
import by.betrayal.visitingservice.exception.NotFoundException;
import by.betrayal.visitingservice.mapper.InstitutionMapper;
import by.betrayal.visitingservice.repository.InstitutionRepository;
import by.betrayal.visitingservice.service.InstitutionService;
import by.betrayal.visitingservice.utils.ThrowableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository repository;
    private final InstitutionMapper mapper;

    @Override
    public List<InstitutionFullDto> findAll() {
        var items = repository.findAll();
        return mapper.toFullDto(items);
    }

    @Override
    public InstitutionFullDto findById(Short id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    public InstitutionFullDto create(InstitutionCreateDto dto) {
        var item = mapper.toEntity(dto);

        var result = repository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    public InstitutionFullDto update(InstitutionUpdateDto dto) {
        var item = findByIdOrThrowNotFoundException(dto.getId());

        mapper.toEntity(item, dto);

        var result = repository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    public InstitutionFullDto delete(Short id) {
        var item = findByIdOrThrowNotFoundException(id);

        repository.delete(item);

        return mapper.toFullDto(item);
    }

    private InstitutionEntity findByIdOrThrowNotFoundException(Short id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
