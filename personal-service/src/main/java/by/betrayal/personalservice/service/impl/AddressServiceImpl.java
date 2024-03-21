package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.dto.address.AddressCreateDto;
import by.betrayal.personalservice.dto.address.AddressFullDto;
import by.betrayal.personalservice.dto.address.AddressUpdateDto;
import by.betrayal.personalservice.entity.AddressEntity;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.mapper.AddressMapper;
import by.betrayal.personalservice.repository.AddressRepository;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.AddressService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public List<AddressFullDto> findAll() {
        var list = addressRepository.findAll();

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public List<AddressFullDto> findAll(Long patientId) {
        var list = addressRepository.findAllByPatientId(patientId);

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public AddressFullDto findById(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public AddressFullDto create(AddressCreateDto dto) {
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        var item = mapper.toEntity(dto);
        item.setPatient(patient);

        var result = addressRepository.saveAndFlush(item);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public AddressFullDto update(AddressUpdateDto dto) {
        var address = findByIdOrThrowNotFoundException(dto.getId());
        var patient = findByIdPersonOrThrowNotFoundException(dto.getPatientId());

        mapper.toEntity(address, dto);
        address.setPatient(patient);

        var result = addressRepository.saveAndFlush(address);

        return mapper.toFullDto(result);
    }

    @Override
    @Transactional
    public AddressFullDto delete(Long id) {
        var item = findByIdOrThrowNotFoundException(id);

        addressRepository.delete(item);

        return mapper.toFullDto(item);
    }

    private AddressEntity findByIdOrThrowNotFoundException(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }

    private PersonEntity findByIdPersonOrThrowNotFoundException(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
