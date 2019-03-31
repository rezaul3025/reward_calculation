package com.reward.api.service;

import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.User;
import com.reward.api.dto.ExerciseDTO;
import com.reward.api.data.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExerciseServiceHandler implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    private UserService userService;

    @Autowired
    public ExerciseServiceHandler(ExerciseRepository exerciseRepository, UserService userService){
        this.exerciseRepository = exerciseRepository;
        this.userService = userService;
    }

    @Override
    public Exercise createExercise(ExerciseDTO exerciseDTO) {

        Exercise exercise= new Exercise(exerciseDTO.getSteps(), new Date(), exerciseDTO.getType());

        Integer userId = exerciseDTO.getUserId();

        User user = userService.findById(userId);

        exercise.setUser(user);

        return exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> findByUser(Integer userId) {

        User user = userService.findById(userId);

        return exerciseRepository.findByUser(user);
    }

}
