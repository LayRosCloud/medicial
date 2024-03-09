package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.service.PersonService;
import by.betrayal.personalservice.utils.LoggerHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
        log.info(LoggerHelper.createLogStart("PersonController", "findAll", ""));
        var items = service.findAll();
        log.info(LoggerHelper.createLogEnd("PersonController", "findAll", "with result size {}"), items.size());
        return items;
    }

    @GetMapping( value = "{id}")
    public PersonFullDto findById(@PathVariable Long id) {
        log.info(LoggerHelper.createLogIdStart("PersonController", "findById", id));
        var item = service.findById(id);
        log.info(LoggerHelper.createLogEnd("PersonController", "findById", "result success"));
        return item;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonFullDto create(@Valid @RequestBody PersonCreateDto dto) {
        log.info(LoggerHelper.createLogStart("PersonController", "create", dto.toString()));
        var item = service.create(dto);
        log.info(LoggerHelper.createLogEnd("PersonController", "create", "object created with id {}"), item.getId());
        return item;
    }

    @PutMapping
    public PersonFullDto update(@Valid @RequestBody PersonUpdateDto dto) {
        log.info(LoggerHelper.createLogStart("PersonController", "update", dto.toString()));
        var item = service.update(dto);
        log.info(LoggerHelper.createLogEnd("PersonController", "update", item.toString()));
        return item;
    }

    @DeleteMapping("{id}")
    public PersonFullDto delete(@PathVariable Long id) {
        log.info(LoggerHelper.createLogIdStart("PersonController", "delete", id));
        var item = service.delete(id);
        log.info(LoggerHelper.createLogEnd("PersonController", "delete", item.toString()));
        return item;
    }
}
