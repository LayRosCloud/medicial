package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.core.IntegrationTest;
import by.betrayal.personalservice.core.database.PersonDao;
import by.betrayal.personalservice.core.utils.equals.PersonEqualsUtils;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.entity.PersonEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PersonControllerTest extends IntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private PersonDao helper;
    @Container
    public static GenericContainer<?> psql
            = new GenericContainer<>(DockerImageName.parse("postgres:16.1"));


    @Autowired
    public PersonControllerTest(MockMvc mockMvc, ObjectMapper mapper, PersonDao helper) {
       this.mockMvc = mockMvc;
       this.mapper = mapper;
       this.helper = helper;
    }
    @BeforeAll
    public static void setUpBase() {
        psql.setWaitStrategy(
                new LogMessageWaitStrategy()
                        .withRegEx(".*database system is ready to accept connections.*\\s")
                        .withTimes(1)
                        .withStartupTimeout(Duration.of(120, ChronoUnit.SECONDS))
        );
        psql.start();
    }
    @BeforeEach
    void setUp() {
        helper.clearDataTable();
    }

    @Test
    void findAll_happyPath() throws Exception {
        var expected = new ArrayList<PersonEntity>();
        expected.add(helper.save());
        expected.add(helper.save());

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var bytes = result.getResponse().getContentAsByteArray();
        var actual =  mapper.readValue(bytes, new TypeReference<List<PersonFullDto>>() {
        });

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.size(), actual.size());
        for (int index = 0; index < expected.size(); index++) {
            var dtoItem = actual.get(index);
            var item = expected.get(index);

            PersonEqualsUtils.assertEqualsDto(item, dtoItem);
        }

    }


}
