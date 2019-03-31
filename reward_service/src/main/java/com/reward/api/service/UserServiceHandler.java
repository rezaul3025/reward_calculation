package com.reward.api.service;

import com.reward.api.data.domain.User;
import com.reward.api.dto.UserDTO;
import com.reward.api.exception.UserNotFoundException;
import com.reward.api.data.repository.UserRepository;
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

    @Override
    public User findById(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(String.format("User not found for given id: %s", id));
        }
        return user;    }


}
