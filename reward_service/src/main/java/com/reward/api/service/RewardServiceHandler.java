package com.reward.api.service;

import com.reward.api.domain.Exercise;
import com.reward.api.domain.Reward;
import com.reward.api.domain.RewardType;
import com.reward.api.domain.User;
import com.reward.api.exception.UserNotFoundException;
import com.reward.api.repository.ExerciseRepository;
import com.reward.api.repository.RewardRepository;
import com.reward.api.repository.UserRepository;
import com.reward.api.service.currency.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RewardServiceHandler implements RewardService {

    private final MathContext DEFAULT_CONTEXT = new MathContext(12, RoundingMode.HALF_DOWN);

    @Value("${reward.price.per1000.steps}")
    BigDecimal rewardPricePer1000Steps;

    @Value("${rewarded.step.threshold}")
    BigDecimal rewardedStepThreshold;

    @Value("${base.currency.iso.code}")
    String baseCurrencyIsoCode;

    private UserRepository userRepository;

    private RewardRepository rewardRepository;

    private ExerciseRepository exerciseRepository;

    private ConverterService converterService;

    @Autowired
    public RewardServiceHandler(UserRepository userRepository, RewardRepository rewardRepository,
                                ExerciseRepository exerciseRepository, ConverterService converterService){
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.converterService = converterService;
    }

    @Override
    public List<Reward> calculate(Integer userId) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new UserNotFoundException(String.format("User not found for given id: %s", userId));
        }

        BigDecimal priceInOneStep = getPriceInOneStep();

        List<Exercise> exercises = exerciseRepository.findByUser(user);

        List<Reward> rewards = new ArrayList<>();

        for(Exercise exercise: exercises){

            exercise.setRewarded(true);

            exerciseRepository.saveAndFlush(exercise);

            BigDecimal price = calculatePrice(priceInOneStep, exercise.getStep());

            Reward reward = generateReward(price, user);

            if(reward == null){
                continue;
            }

            reward.setExercise(exercise);

            rewardRepository.saveAndFlush(reward);

            rewards.add(reward);
        }

        return rewards;
    }

    private BigDecimal calculatePrice(BigDecimal priceInOneStep, Integer totalSteps) {
        return priceInOneStep.multiply(new BigDecimal(totalSteps), DEFAULT_CONTEXT);
    }

    private Reward generateReward(@NotNull  BigDecimal price, @NotNull User user) {

        String userCurrencyISOCode = user.getCurrencyISOCode();

        if(baseCurrencyIsoCode == null || userCurrencyISOCode == null){
            return null;
        }

        BigDecimal convertedPrice = converterService.convert(price, baseCurrencyIsoCode, userCurrencyISOCode);

        Reward reward = new Reward(price, convertedPrice, RewardType.CASH, new Date());

        reward.setUser(user);

        return reward;
    }

    private BigDecimal getPriceInOneStep() {
        return rewardPricePer1000Steps.divide(rewardedStepThreshold, DEFAULT_CONTEXT);
    }
}
