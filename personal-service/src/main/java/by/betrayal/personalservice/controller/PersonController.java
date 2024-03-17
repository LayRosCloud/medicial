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

    private static final String CONTROLLER_NAME = "Person";

    @GetMapping
    public ResponseEntity<List<PersonFullDto>> findAll() {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
        var items = service.findAll();
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", items.size());
        return ResponseEntity.ok(items);
    }

    @GetMapping( ENDPOINT_ID)
    public ResponseEntity<PersonFullDto> findById(@PathVariable Long id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<PersonFullDto> create(@Valid @RequestBody PersonCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonFullDto> update(@Valid @RequestBody PersonUpdateDto dto) {
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, item.toString());
        return ResponseEntity.ok(item);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<PersonFullDto> delete(@PathVariable Long id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return ResponseEntity.ok(item);
    }
}
