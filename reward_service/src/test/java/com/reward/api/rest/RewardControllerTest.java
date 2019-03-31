package com.reward.api.rest;

import com.reward.api.TestConfig;
import com.reward.api.data.domain.Reward;
import com.reward.api.data.domain.RewardType;
import com.reward.api.service.RewardService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RewardControllerTest extends TestConfig {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private RewardService rewardService;

    Reward reward = new Reward(BigDecimal.valueOf(100.12) , BigDecimal.valueOf(89.00), RewardType.CASH, new Date());
    Reward reward1 = new Reward(BigDecimal.valueOf(103.12) , BigDecimal.valueOf(94.00), RewardType.CASH, new Date());


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void calculateForUserTest() throws Exception {

        when(rewardService.calculateForUser(100)).thenReturn(Arrays.asList(reward, reward1));

        mockMvc.perform(post("/rewards/user/100"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].priceInEuro").value(100.12))
                .andExpect(jsonPath("$[0].convertedPrice").value(89.00))
                .andExpect(jsonPath("$[1].priceInEuro").value(103.12))
                .andExpect(jsonPath("$[1].convertedPrice").value(94.00));
    }

    @Test
    public void findByUserTest() throws Exception {

        when(rewardService.findByUser(100)).thenReturn(Arrays.asList(reward, reward1));

        mockMvc.perform(get("/rewards/user/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].priceInEuro").value(100.12))
                .andExpect(jsonPath("$[0].convertedPrice").value(89.00))
                .andExpect(jsonPath("$[1].priceInEuro").value(103.12))
                .andExpect(jsonPath("$[1].convertedPrice").value(94.00));

    }
}