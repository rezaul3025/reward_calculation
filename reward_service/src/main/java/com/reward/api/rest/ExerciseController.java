package com.reward.api.rest;

import com.reward.api.data.domain.Exercise;
import com.reward.api.dto.ExerciseDTO;
import com.reward.api.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody @Valid ExerciseDTO exerciseDTO){
        return new ResponseEntity<>(exerciseService.createExercise(exerciseDTO),  HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> findAll(){
        return new ResponseEntity<>(exerciseService.findAll(),  HttpStatus.OK
        );
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<Exercise>> findByUser(@PathVariable("id") Integer userId){
        return new ResponseEntity<>(exerciseService.findByUser(userId),  HttpStatus.OK);
    }

}
