package com.reward.api.service;

import com.reward.api.TestConfig;
import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.ExerciseType;
import com.reward.api.data.domain.Reward;
import com.reward.api.data.domain.User;
import com.reward.api.data.repository.ExerciseRepository;
import com.reward.api.data.repository.UserRepository;
import com.reward.api.service.external.OpenExchangeApiService;
import com.reward.api.service.external.mock.MockOpenExchangeApiService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class RewardServiceHandlerTest extends TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private MockOpenExchangeApiService mockOpenExchangeApiService;

    @MockBean
    OpenExchangeApiService openExchangeApiService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user =  createUser();

        createExerciseForUser(user);

        when(openExchangeApiService.getExchangeRateApiResponse()).thenReturn(mockOpenExchangeApiService.getExchangeRateApiResponse());
    }

    @Test
    public void calculateForUserTest() {
        List<Reward> rewards = rewardService.calculateForUser(user.getId());

        assertThat(rewards).isNotNull().hasSize(2);
        assertThat(rewards.get(0)).isNotNull();
        assertThat(rewards.get(0).getPriceInEuro().doubleValue()).isNotNull().isEqualTo(1.200);
        assertThat(rewards.get(0).getConvertedPrice().doubleValue()).isNotNull().isEqualTo(1.02411715708);

        assertThat(rewards.get(1)).isNotNull();
        assertThat(rewards.get(1).getPriceInEuro().doubleValue()).isNotNull().isEqualTo(0.900);
        assertThat(rewards.get(1).getConvertedPrice().doubleValue()).isNotNull().isEqualTo(0.768087867809);
    }

    private User createUser(){
        User user = new User("test", "UK", "GBP");
       return userRepository.save(user);
    }

    private void createExerciseForUser(User user){
        Exercise exercise= new Exercise(1200, new Date(), ExerciseType.WALKING);
        exercise.setUser(user);
        exerciseRepository.save(exercise);

        Exercise exercise1= new Exercise(900, new Date(), ExerciseType.WALKING);
        exercise1.setUser(user);
        exerciseRepository.save(exercise1);

    }

}