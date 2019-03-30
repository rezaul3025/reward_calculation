package com.reward.api.config;

import com.reward.api.dto.UserDTO;
import com.reward.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

@Configuration
@Profile({"dev","test"})
public class InitialDataGeneration {

    @Autowired
    private UserService userService;

    @EventListener
    public void onAppReady(ApplicationReadyEvent event) {
        addTestUsers();
    }

    private void addTestUsers()
    {
        userService.add(new UserDTO("test", "UK", "GBP"));
        userService.add(new UserDTO("test1", "DE", "EUR"));
    }
}
