package com.validation.assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.validation.assignment.dto.RegisterUserDto;

public interface UserService {

    RegisterUserDto registerUser(RegisterUserDto registerUserDto) throws JsonProcessingException;
}
