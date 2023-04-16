package com.objis.masterclass2.controller;

import com.objis.masterclass2.repository.StudentRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mourad Jebouai <mouradjebouai@gmail.com>
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("StudentControllerTest")
@DisplayName("unit testing student controller endpoints")

public class StudentControllerTest {
    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public MockMvc mvc;

    private JSONObject json;

    @BeforeAll
    @AfterAll
    public void clearDtatabase(){
            this.studentRepository.deleteAll();
            json=null;
    }

    @Test
    @Order(value = 1)
    @DisplayName("create a student")
    public void testThatWecanCreatStudent() throws Exception{
        MvcResult result= this.mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/rest/students")
                        .content("{"
                                        + "\"firstName\": \"Mourad\","
                                        + "\"lastName\": \"Jebouai\","
                                        + "\"email\": \"mouradjebouai@gmail.com\""
                        +"}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Mourad")))
                .andExpect(jsonPath("$.lastName", is("Jebouai")))
                .andExpect(jsonPath("$.email", is("mouradjebouai@gmail.com")))
                .andReturn();

        json = new JSONObject(result.getResponse().getContentAsString());

    }


}
