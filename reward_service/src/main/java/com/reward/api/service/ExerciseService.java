package com.reward.api.service;

import com.reward.api.domain.Exercise;
import com.reward.api.dto.ExerciseDTO;

public interface ExerciseService {

    Exercise createExercise(ExerciseDTO exerciseDTO);
}
