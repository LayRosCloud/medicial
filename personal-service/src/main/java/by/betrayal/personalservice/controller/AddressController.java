package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.dto.address.AddressCreateDto;
import by.betrayal.personalservice.dto.address.AddressFullDto;
import by.betrayal.personalservice.dto.address.AddressUpdateDto;
import by.betrayal.personalservice.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    private static final String END_POINT = "v1/addresses";
    private static final String END_POINT_WITH_ID = "v1/addresses/{id}";
    private static final String END_POINT_WITH_PATIENT_ID = "v1/people/{patientId}/addresses";

    @GetMapping(END_POINT)
    public ResponseEntity<List<AddressFullDto>> findAll() {
        var list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(END_POINT_WITH_PATIENT_ID)
    public ResponseEntity<List<AddressFullDto>> findAll(@PathVariable Long patientId) {
        var list = service.findAll(patientId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(END_POINT_WITH_ID)
    public ResponseEntity<AddressFullDto> findById(@PathVariable Long id) {
        var item = service.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(END_POINT)
    public ResponseEntity<AddressFullDto> create(@Valid @RequestBody AddressCreateDto dto) {
        var item = service.create(dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(END_POINT)
    public ResponseEntity<AddressFullDto> update(@Valid @RequestBody AddressUpdateDto dto) {
        var item = service.update(dto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
