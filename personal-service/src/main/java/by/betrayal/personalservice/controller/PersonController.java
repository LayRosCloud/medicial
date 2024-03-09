package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/people")
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<PersonFullDto> findAll() {
        log.info("--- Start request in controller `PersonController` - findAll ---");
        var items = service.findAll();
        log.info("--- End request in controller `PersonController` - findAll with result {} ---", items.size());
        return items;
    }

    @GetMapping( value = "{id}")
    public PersonFullDto findById(@PathVariable Long id) {
        log.info("--- Start request in controller `PersonController` - findById with id {} ---", id);
        var item = service.findById(id);
        log.info("--- End request in controller `PersonController` - findById result success ---");
        return item;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonFullDto create(@Valid @RequestBody PersonCreateDto dto) {
        log.info("--- Start request in controller `PersonController` - create ---");
        var item = service.create(dto);
        log.info("--- End request in controller `PersonController` - create result id {} ---", item.getId());
        return item;
    }

    @PutMapping
    public PersonFullDto update(@Valid @RequestBody PersonUpdateDto dto) {
        log.info("--- Start request in controller `PersonController` - update {}---", dto.getId());
        var item = service.update(dto);
        log.info("--- End request in controller `PersonController` - update result id {} ---", item.getId());
        return item;
    }

    @DeleteMapping("{id}")
    public PersonFullDto delete(@PathVariable Long id) {
        log.info("--- Start request in controller `PersonController` - delete {}---", id);
        var item = service.delete(id);
        log.info("--- End request in controller `PersonController` - delete result id {} ---", item.getId());
        return item;
    }
}
