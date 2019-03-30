package com.reward.api.rest;

import com.reward.api.domain.Exercise;
import com.reward.api.dto.ExerciseDTO;
import com.reward.api.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Exercise> create(@RequestBody @Valid ExerciseDTO exerciseDTO){
        return new ResponseEntity<>(exerciseService.createExercise(exerciseDTO),  HttpStatus.CREATED);
    }

}
