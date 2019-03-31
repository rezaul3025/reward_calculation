package com.reward.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reward.api.TestConfig;
import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.ExerciseType;
import com.reward.api.data.domain.User;
import com.reward.api.dto.ExerciseDTO;
import com.reward.api.service.ExerciseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExerciseControllerTest extends TestConfig {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExerciseService exerciseService;

    private JacksonTester<ExerciseDTO> jsonTesterExercise;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void createTest() throws Exception {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setSteps(1000);
        exerciseDTO.setType(ExerciseType.RUNNING);
        exerciseDTO.setUserId(100);
        exerciseDTO.setDate(new Date());
        final String categoryDTOJson = jsonTesterExercise.write(exerciseDTO).getJson();

        Exercise exercise = new Exercise(1000, new Date(), ExerciseType.RUNNING);
        exercise.setId(1001);
        exercise.setUser(new User());

        when(exerciseService.createExercise(exerciseDTO)).thenReturn(exercise);

        mockMvc.perform(post("/exercises")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(categoryDTOJson))
                .andExpect(status().isCreated())
                .andReturn();
    }
}