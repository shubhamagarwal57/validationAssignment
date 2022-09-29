package com.validation.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.validation.assignment.dto.RegisterUserDto;
import com.validation.assignment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "controller which exposes user related api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation("Rest Api to register the user")
    public ResponseEntity<RegisterUserDto> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) throws JsonProcessingException {
        return new ResponseEntity<>(userService.registerUser(registerUserDto), HttpStatus.CREATED);
    }
}
