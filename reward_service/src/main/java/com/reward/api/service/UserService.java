package com.reward.api.service;

import com.reward.api.domain.User;
import com.reward.api.dto.UserDTO;

public interface UserService {

    User add(UserDTO userDTO);
}
