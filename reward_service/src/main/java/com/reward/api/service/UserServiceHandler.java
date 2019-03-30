package com.reward.api.service;

import com.reward.api.domain.User;
import com.reward.api.dto.UserDTO;
import com.reward.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceHandler implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User add(UserDTO userDTO) {

        User user = new User(userDTO.getName(), userDTO.getCountryISOCode(), userDTO.getCurrencyISOCode());

        return userRepository.save(user);
    }
}
