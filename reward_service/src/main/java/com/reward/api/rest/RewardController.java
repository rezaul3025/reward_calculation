package com.reward.api.rest;

import com.reward.api.data.domain.Reward;
import com.reward.api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rewards")
public class RewardController {

	private RewardService rewardService;

	@Autowired
	public RewardController(RewardService rewardService){
		this.rewardService = rewardService;
	}
	
	@GetMapping(value="")
	public String reward() {
		return "Reward";
	}

	@PostMapping(value = "/user/{id}")
	public ResponseEntity<List<Reward>> calculate(@PathVariable("id") Integer userId){
		return new ResponseEntity<>(rewardService.calculateForUser(userId), HttpStatus.CREATED);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<Reward>> findByUser(@PathVariable("id") Integer userId){
		return new ResponseEntity<>(rewardService.findByUser(userId), HttpStatus.OK);
	}
}
