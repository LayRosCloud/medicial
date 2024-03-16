package by.betrayal.personalservice.service.impl;

import by.betrayal.personalservice.client.controller.VisitingClient;
import by.betrayal.personalservice.dto.person.PersonCreateDto;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.dto.person.PersonUpdateDto;
import by.betrayal.personalservice.entity.PersonEntity;
import by.betrayal.personalservice.exception.ServiceNotAvailableException;
import by.betrayal.personalservice.mapper.PersonMapper;
import by.betrayal.personalservice.repository.PersonRepository;
import by.betrayal.personalservice.service.PersonService;
import by.betrayal.personalservice.utils.ThrowableUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;
    private final VisitingClient visitClient;
    private final RabbitTemplate template;

    @Value("${spring.rabbitmq.exchange-notifications.name}")
    private String exchange;

    @Value("${spring.rabbitmq.key}")
    private String routingKey;

    @Override
    @Transactional
    public List<PersonFullDto> findAll() {
        var list = repository.findAll();

        return mapper.toFullDto(list);
    }

    @Override
    @Transactional
    public PersonFullDto findById(Long id) {
        var item = findPersonByIdOrThrowNotFound(id);

        return mapper.toFullDto(item);
    }

    @Override
    @Transactional
    public PersonFullDto create(PersonCreateDto dto) {
        var item = mapper.toEntity(dto);
        var image = dto.getImage();

        if (image != null) {
            var urlImage = saveImage(image);
            item.setImage(urlImage);
        }
        var response = repository.saveAndFlush(item);
        template.convertAndSend(exchange, routingKey, response);

        return mapper.toFullDto(response);
    }

    @Override
    @Transactional
    public PersonFullDto update(PersonUpdateDto dto) {
        var item = findPersonByIdOrThrowNotFound(dto.getId());

        mapper.toEntity(item, dto);

        var image = dto.getImage();

        if (image != null) {
            var urlImage = saveImage(image);
            item.setImage(urlImage);
        }

        var response = repository.saveAndFlush(item);

        return mapper.toFullDto(response);
    }

    @Override
    @Transactional
    public PersonFullDto delete(Long id) {
        var item  = findPersonByIdOrThrowNotFound(id);
        repository.delete(item);
        tryDeleteVisitsAndDiagnostics(id);

        return mapper.toFullDto(item);
    }

    private void tryDeleteVisitsAndDiagnostics(Long patientId) {
        try {
            deleteAllVisitsByPatientId(patientId);
            deleteAllDiagnosticsByPatientId(patientId);
        } catch (Exception ex) {
            throw new ServiceNotAvailableException();
        }
    }

    private void deleteAllVisitsByPatientId(Long patientId) {
        visitClient.deleteAllVisitsByPatientId(patientId);
    }

    private void deleteAllDiagnosticsByPatientId(Long patientId) {
        visitClient.deleteAllDiagnosticsByPatientId(patientId);
    }

    private String saveImage(MultipartFile fileImage) {
        // TODO: service
        return UUID.randomUUID().toString();
    }

    private PersonEntity findPersonByIdOrThrowNotFound(Long id) {
        return repository.findById(id).orElseThrow(() ->
                ThrowableUtils.throwNotFoundException(id)
        );
    }
}
