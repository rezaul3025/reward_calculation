package com.reward.api.service;

import com.reward.api.data.domain.Exercise;
import com.reward.api.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {

    Exercise createExercise(ExerciseDTO exerciseDTO);

    List<Exercise> findAll();

    List<Exercise> findByUser(Integer userId);
}
