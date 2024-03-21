package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.contact.ContactCreateDto;
import by.betrayal.personalservice.dto.contact.ContactFullDto;
import by.betrayal.personalservice.dto.contact.ContactUpdateDto;
import by.betrayal.personalservice.service.ContactService;
import by.betrayal.personalservice.utils.LoggerUtils;
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

    public static final String CONTROLLER_NAME = "Contact";

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<ContactFullDto>> findAll() {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
        var list = service.findAll();
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_PATIENT_ID)
    public ResponseEntity<List<ContactFullDto>> findAll(@PathVariable Long patientId) {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "patientId", "patientId="+patientId);
        var list = service.findAll(patientId);
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "patientId", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactFullDto> findById(@PathVariable Long id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<ContactFullDto> create(@Valid @RequestBody ContactCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ENDPOINT)
    public ResponseEntity<ContactFullDto> update(@Valid @RequestBody ContactUpdateDto dto) {
        LoggerUtils.createLogUpdateStart(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping(ENDPOINT_BY_ID)
    public ResponseEntity<ContactFullDto> delete(@PathVariable Long id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
