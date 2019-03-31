package com.reward.api.service;

import com.reward.api.config.ConfigService;
import com.reward.api.data.domain.Exercise;
import com.reward.api.data.domain.Reward;
import com.reward.api.data.domain.RewardType;
import com.reward.api.data.domain.User;
import com.reward.api.exception.ExerciseNotFoundException;
import com.reward.api.data.repository.ExerciseRepository;
import com.reward.api.data.repository.RewardRepository;
import com.reward.api.service.currency.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RewardServiceHandler implements RewardService {

    private ConfigService configService;

    private UserService userService;

    private RewardRepository rewardRepository;

    private ExerciseRepository exerciseRepository;

    private ConverterService converterService;

    @Autowired
    public RewardServiceHandler(ConfigService configService, UserService userService, RewardRepository rewardRepository,
                                ExerciseRepository exerciseRepository, ConverterService converterService){
        this.configService = configService;
        this.rewardRepository = rewardRepository;
        this.userService = userService;
        this.exerciseRepository = exerciseRepository;
        this.converterService = converterService;
    }

    @Override
    public List<Reward> calculateForUser(Integer userId) {

        User user = userService.findById(userId);

        List<Exercise> exercises = exerciseRepository.findByUser(user);

        List<Reward> rewards = new ArrayList<>();

        for(Exercise exercise: exercises){

            if(exercise.getReward() != null){
                continue;
            }

            exerciseRepository.saveAndFlush(exercise);

           Reward reward= calculateForExercise(exercise.getId());

           if(reward !=  null){
               rewards.add(reward);
           }
        }

        return rewards;
    }

    @Override
    public Reward calculateForExercise(Integer exerciseId) {

        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);

        if(exercise == null){
            throw new ExerciseNotFoundException(String.format("Exercise not found for the id : %s", exerciseId));
        }

        BigDecimal priceInOneStep = getPriceInOneStep();

        BigDecimal price = calculatePrice(priceInOneStep, exercise.getSteps());

        Reward reward = generateReward(price, exercise.getUser());

        if(reward == null){
            return null;
        }

        reward.setExercise(exercise);

        rewardRepository.saveAndFlush(reward);

        return reward;
    }

    @Override
    public List<Reward> findByUser(Integer userId) {
        User user = userService.findById(userId);

        return rewardRepository.findByUser(user);

    }

    private BigDecimal calculatePrice(BigDecimal priceInOneStep, Integer totalSteps) {
        return priceInOneStep.multiply(new BigDecimal(totalSteps), configService.getDefaultContext());
    }

    private Reward generateReward(@NotNull  BigDecimal price, @NotNull User user) {

        String userCurrencyISOCode = user.getCurrencyISOCode();

        String baseCurrencyIsoCode = configService.getBaseCurrencyIsoCode();

        if(baseCurrencyIsoCode == null || userCurrencyISOCode == null){
            return null;
        }

        BigDecimal convertedPrice = converterService.convert(price, baseCurrencyIsoCode, userCurrencyISOCode);

        Reward reward = new Reward(price, convertedPrice, RewardType.CASH, new Date());

        reward.setUser(user);

        return reward;
    }

    private BigDecimal getPriceInOneStep() {
        return configService.getRewardPricePer1000Steps()
                .divide(new BigDecimal(configService.getRewardedStepThreshold()), configService.getDefaultContext());
    }
}
