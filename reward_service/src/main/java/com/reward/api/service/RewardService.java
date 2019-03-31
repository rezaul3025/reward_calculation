package com.reward.api.service;

import com.reward.api.data.domain.Reward;

import java.util.List;

public interface RewardService {

    List<Reward> calculateForUser(Integer userId);

    Reward calculateForExercise(Integer exerciseId);

    List<Reward> findByUser(Integer userId);
}
