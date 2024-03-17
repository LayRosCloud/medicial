package by.betrayal.personalservice.controller;

import by.betrayal.personalservice.core.IntegrationTest;
import by.betrayal.personalservice.core.database.PersonDao;
import by.betrayal.personalservice.core.utils.creator.PersonCreationUtils;
import by.betrayal.personalservice.core.utils.equals.PersonEqualsUtils;
import by.betrayal.personalservice.dto.person.PersonFullDto;
import by.betrayal.personalservice.entity.PersonEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PersonControllerTest extends IntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final PersonDao helper;

    @Container
    private PostgreSQLContainer<?> psql
            = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.1"));


    @Autowired
    public PersonControllerTest(MockMvc mockMvc, ObjectMapper mapper, PersonDao helper) {
       this.mockMvc = mockMvc;
       this.mapper = mapper;
       this.helper = helper;
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
    }

    @Test
    void findByIdValidId_happyPath() throws Exception {
        var expected = helper.save();

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/people/"+ expected.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var bytes = result.getResponse().getContentAsByteArray();
        var actual = mapper.readValue(bytes,  PersonFullDto.class);

        Assertions.assertNotNull(actual);
        PersonEqualsUtils.assertEqualsDto(expected, actual);
    }

    @Test
    void findByIdInvalidId_happyPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/people/"+ 999L))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void createTest_happyPath() throws Exception {
        var item = PersonCreationUtils.generateCreateDto();
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var bytes = result.getResponse().getContentAsByteArray();
        var response = mapper.readValue(bytes, PersonFullDto.class);

        Assertions.assertNotNull(response);
        PersonEqualsUtils.assertEqualsDto(item, response);
    }

    @Test
    void createTestInvalidDto_happyPath() throws Exception {
        var item = PersonCreationUtils.generateCreateInvalidDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item))
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void updateTest_happyPath() throws Exception {
        var help = helper.save();
        var item = PersonCreationUtils.generateUpdateDto(help.getId());


        var result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var bytes = result.getResponse().getContentAsByteArray();
        var response = mapper.readValue(bytes, PersonFullDto.class);

        Assertions.assertNotNull(response);
        PersonEqualsUtils.assertEqualsDto(item, response);
    }

    @Test
    void deleteTest_happyPath() throws Exception {
        var item = helper.save();

        var result = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/people/" + item.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var bytes = result.getResponse().getContentAsByteArray();
        var response = mapper.readValue(bytes, PersonFullDto.class);

        Assertions.assertNotNull(response);
        PersonEqualsUtils.assertEqualsDto(item, response);
    }

    @Test
    void deleteTestInvalidId_happyPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/people/" + 999L))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void updateTestInvalidId_happyPath() throws Exception {
        var item = PersonCreationUtils.generateUpdateInvalidIdDto();
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item))
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void updateTestInvalid_happyPath() throws Exception {
        var help = helper.save();
        var item = PersonCreationUtils.generateUpdateInvalidDto(help.getId());
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(item))
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }


}
