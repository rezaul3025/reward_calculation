package com.reward.api.service;

import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.ExerciseType;
import com.reward.api.data.repository.ExerciseRepository;
import com.reward.api.dto.ExerciseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceHandlerTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ExerciseServiceHandler exerciseService;

    @Captor
    private ArgumentCaptor<Exercise> exerciseCaptor;

    @Captor
    private ArgumentCaptor<Integer> userIdCaptor;

    @Test
    public void createExerciseTest() {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        exerciseDTO.setSteps(1000);
        exerciseDTO.setType(ExerciseType.RUNNING);
        exerciseDTO.setUserId(100);
        exerciseDTO.setDate(new Date());

        exerciseService.createExercise(exerciseDTO);

        verify(userService, timeout(1)).findById(userIdCaptor.capture());

        assertThat(userIdCaptor.getValue()).isNotNull().isEqualTo(100);

        verify(exerciseRepository, timeout(1)).saveAndFlush(exerciseCaptor.capture());

        assertThat(exerciseCaptor.getValue()).isNotNull();
        assertThat(exerciseCaptor.getValue().getSteps()).isEqualTo(1000);
        assertThat(exerciseCaptor.getValue().getType()).isEqualTo(ExerciseType.RUNNING);
    }

    @Test
    public void findAllTest() {
        exerciseService.findAll();
        verify(exerciseRepository,times(1)).findAll();
    }

    @Test
    public void findByUserTest() {
        exerciseService.findByUser(100);
        verify(userService, times(1)).findById(userIdCaptor.capture());
        assertThat(userIdCaptor.getValue()).isEqualTo(100);
    }
}