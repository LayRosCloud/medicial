package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.service.PersonService;
import by.betrayal.personalservice.utils.LoggerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = PersonController.ENDPOINT)
public class PersonController {

    private final PersonService service;
    public static final String ENDPOINT = "v1/people";
    public static final String ENDPOINT_ID = "{id}";

    @GetMapping
    public ResponseEntity<List<PersonFullDto>> findAll() {
        log.info(LoggerUtils.createLogStart("PersonController", "findAll", ""));
        var items = service.findAll();
        log.info(LoggerUtils.createLogEnd("PersonController", "findAll", "with result size {}"), items.size());
        return ResponseEntity.ok(items);
    }

    @GetMapping( ENDPOINT_ID)
    public ResponseEntity<PersonFullDto> findById(@PathVariable Long id) {
        log.info(LoggerUtils.createLogIdStart("PersonController", "findById", id));
        var item = service.findById(id);
        log.info(LoggerUtils.createLogEnd("PersonController", "findById", "result success"));
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<PersonFullDto> create(@Valid @RequestBody PersonCreateDto dto) {
        log.info(LoggerUtils.createLogStart("PersonController", "create", dto.toString()));
        var item = service.create(dto);
        log.info(LoggerUtils.createLogEnd("PersonController", "create", "object created with id {}"), item.getId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonFullDto> update(@Valid @RequestBody PersonUpdateDto dto) {
        log.info(LoggerUtils.createLogStart("PersonController", "update", dto.toString()));
        var item = service.update(dto);
        log.info(LoggerUtils.createLogEnd("PersonController", "update", item.toString()));
        return ResponseEntity.ok(item);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<PersonFullDto> delete(@PathVariable Long id) {
        log.info(LoggerUtils.createLogIdStart("PersonController", "delete", id));
        var item = service.delete(id);
        log.info(LoggerUtils.createLogEnd("PersonController", "delete", item.toString()));
        return ResponseEntity.ok(item);
    }
}
