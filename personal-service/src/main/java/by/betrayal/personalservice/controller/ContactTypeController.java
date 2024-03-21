package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.contact.type.ContactTypeCreateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeUpdateDto;
import by.betrayal.personalservice.service.ContactTypeService;
import by.betrayal.personalservice.utils.LoggerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ContactTypeController.ENDPOINT)
@RequiredArgsConstructor
public class ContactTypeController {

    private final ContactTypeService service;
    public static final String ENDPOINT = "v1/contacts/types";
    public static final String ENDPOINT_BY_ID = "{id}";
    private static final String CONTROLLER_NAME = "ContactType";

    @GetMapping
    public ResponseEntity<List<ContactTypeFullDto>> findAll() {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
        var list = service.findAll();
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactTypeFullDto> findById(@PathVariable Short id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactTypeFullDto> create(@Valid @RequestBody ContactTypeCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ContactTypeFullDto> update(@Valid @RequestBody ContactTypeUpdateDto dto) {
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactTypeFullDto> delete(@PathVariable Short id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
