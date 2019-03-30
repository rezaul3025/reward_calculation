package com.reward.api;

import com.reward.api.rest.RewardController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


public class RewardApiApplicationTests extends TestConfig{

	@Autowired
	private RewardController rewardController;

	@Test
	public void contextLoads() {
		assertThat(rewardController).isNotNull();
	}

}
