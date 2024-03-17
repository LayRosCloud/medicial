package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.address.AddressCreateDto;
import by.betrayal.personalservice.dto.address.AddressFullDto;
import by.betrayal.personalservice.dto.address.AddressUpdateDto;
import by.betrayal.personalservice.service.AddressService;
import by.betrayal.personalservice.utils.LoggerUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService service;

    private static final String END_POINT = "v1/addresses";
    private static final String END_POINT_WITH_ID = "v1/addresses/{id}";
    private static final String END_POINT_WITH_PATIENT_ID = "v1/people/{patientId}/addresses";

    private static final String CONTROLLER_NAME = "Address";

    @GetMapping(END_POINT)
    public ResponseEntity<List<AddressFullDto>> findAll() {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "", "");
        var list = service.findAll();
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(END_POINT_WITH_PATIENT_ID)
    public ResponseEntity<List<AddressFullDto>> findAll(@PathVariable Long patientId) {
        LoggerUtils.createLogFindAllStart(CONTROLLER_NAME, "patientId", "patientId=" + patientId);
        var list = service.findAll(patientId);
        LoggerUtils.createLogFindAllEnd(CONTROLLER_NAME, "patientId", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(END_POINT_WITH_ID)
    public ResponseEntity<AddressFullDto> findById(@PathVariable Long id) {
        LoggerUtils.createLogFindByIdStart(CONTROLLER_NAME, id);
        var item = service.findById(id);
        LoggerUtils.createLogFindByIdEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(END_POINT)
    public ResponseEntity<AddressFullDto> create(@Valid @RequestBody AddressCreateDto dto) {
        LoggerUtils.createLogCreateStart(CONTROLLER_NAME, dto.toString());
        var item = service.create(dto);
        LoggerUtils.createLogCreateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(END_POINT)
    public ResponseEntity<AddressFullDto> update(@Valid @RequestBody AddressUpdateDto dto) {
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, dto.toString());
        var item = service.update(dto);
        LoggerUtils.createLogUpdateEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(END_POINT_WITH_ID)
    public ResponseEntity<AddressFullDto> delete(@PathVariable Long id) {
        LoggerUtils.createLogDeleteStart(CONTROLLER_NAME, id);
        var item = service.delete(id);
        LoggerUtils.createLogDeleteEnd(CONTROLLER_NAME, item.toString());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
