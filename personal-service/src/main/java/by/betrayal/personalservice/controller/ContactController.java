package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactFullDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;
import by.betrayal.personalservice.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    public static final String ENDPOINT = "v1/contacts";
    public static final String ENDPOINT_BY_ID = "v1/contacts/{id}";
    public static final String ENDPOINT_BY_PATIENT_ID = "v1/people/{patientId}/contacts";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<ContactFullDto>> findAll() {
        var list = service.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<ContactFullDto>> findAll(@PathVariable Long patientId) {
        var list = service.findAll(patientId);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<ContactFullDto> create(@Valid @RequestBody ContactCreateDto dto) {
        var item = service.create(dto);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<ContactFullDto> update(@Valid @RequestBody ContactUpdateDto dto) {
        var item = service.update(dto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactFullDto> delete(@PathVariable Long id) {
        var item = service.delete(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
