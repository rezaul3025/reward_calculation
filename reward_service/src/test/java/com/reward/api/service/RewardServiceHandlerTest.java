package com.reward.api.service;

import com.reward.api.TestConfig;
import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.ExerciseType;
import com.reward.api.data.domain.Reward;
import com.reward.api.data.domain.User;
import com.reward.api.data.repository.ExerciseRepository;
import com.reward.api.data.repository.RewardRepository;
import com.reward.api.data.repository.UserRepository;
import com.reward.api.service.currency.ConverterService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class RewardServiceHandlerTest extends TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private RewardService rewardService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user =  createUser();

        createExerciseForUser(user);
    }

    @Test
    public void calculateTest() {
        List<Reward> rewards = rewardService.calculateForUser(user.getId());

        assertThat(rewards).isNotNull().hasSize(2);
    }

    private User createUser(){
        User user = new User("test", "UK", "GBP");
       return userRepository.save(user);
    }

    private void createExerciseForUser(User user){
        Exercise exercise= new Exercise(1200, new Date(), ExerciseType.WALKING, false);
        exercise.setUser(user);
        exerciseRepository.save(exercise);

        Exercise exercise1= new Exercise(900, new Date(), ExerciseType.WALKING, false);
        exercise1.setUser(user);
        exerciseRepository.save(exercise1);

    }

}