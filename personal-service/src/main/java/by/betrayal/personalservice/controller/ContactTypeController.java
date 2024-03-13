package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.contact.type.ContactTypeCreateDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeFullDto;
import by.betrayal.personalservice.dto.contact.type.ContactTypeUpdateDto;
import by.betrayal.personalservice.service.ContactTypeService;
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

    @GetMapping
    public ResponseEntity<List<ContactTypeFullDto>> findAll() {
        var list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactTypeFullDto> findById(@PathVariable Short id) {
        var item = service.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactTypeFullDto> create(@Valid @RequestBody ContactTypeCreateDto dto) {
        var item = service.create(dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ContactTypeFullDto> update(@Valid @RequestBody ContactTypeUpdateDto dto) {
        var item = service.update(dto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactTypeFullDto> delete(@PathVariable Short id) {
        var item = service.delete(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
