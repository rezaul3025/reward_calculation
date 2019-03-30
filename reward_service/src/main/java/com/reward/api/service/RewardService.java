package com.reward.api.service;

import com.reward.api.domain.Reward;

import java.util.List;

public interface RewardService {

    List<Reward> calculate(Integer userId);
}
