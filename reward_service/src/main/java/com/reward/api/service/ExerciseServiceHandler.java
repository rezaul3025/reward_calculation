package com.reward.api.service;

import com.reward.api.domain.Exercise;
import com.reward.api.domain.User;
import com.reward.api.dto.ExerciseDTO;
import com.reward.api.exception.UserNotFoundException;
import com.reward.api.repository.ExerciseRepository;
import com.reward.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExerciseServiceHandler implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    private UserRepository userRepository;

    @Autowired
    public ExerciseServiceHandler(ExerciseRepository exerciseRepository, UserRepository userRepository){
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Exercise createExercise(ExerciseDTO exerciseDTO) {

        Exercise exercise= new Exercise(exerciseDTO.getSteps(), new Date(), exerciseDTO.getType(), exerciseDTO.getRewarded());

        Integer userId = exerciseDTO.getUserId();

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new UserNotFoundException(String.format("User not found for given id: %s", userId));
        }

        exercise.setUser(user);

        return exerciseRepository.saveAndFlush(exercise);
    }
}
