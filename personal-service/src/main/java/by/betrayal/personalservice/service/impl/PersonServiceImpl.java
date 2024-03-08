package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.mapper.PersonMapper;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Override
    public List<PersonFullDto> findAll() {
        return null;
    }

    @Override
    public PersonFullDto findById(Long id) {
        return null;
    }

    @Override
    public PersonFullDto create(PersonCreateDto dto) {
        return null;
    }

    @Override
    public PersonFullDto update(PersonUpdateDto dto) {
        return null;
    }

    @Override
    public PersonFullDto delete(Long id) {
        return null;
    }
}
