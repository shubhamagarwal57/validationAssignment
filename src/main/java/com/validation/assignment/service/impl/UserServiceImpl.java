package com.validation.assignment.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.validation.assignment.dto.RegisterUserDto;
import com.validation.assignment.entity.Address;
import com.validation.assignment.entity.User;
import com.validation.assignment.exception.ResourceAlreadyPresent;
import com.validation.assignment.repository.AddressRepository;
import com.validation.assignment.repository.UserRepository;
import com.validation.assignment.service.AddressService;
import com.validation.assignment.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository, AddressRepository addressRepository,AddressService addressService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @Override
    public RegisterUserDto registerUser(RegisterUserDto registerUserDto) throws JsonProcessingException {
        User user = mapToEntity(registerUserDto);
        if(userRepository.existsByEmail(registerUserDto.getEmail())){
            logger.error("Exception occurred as the mail id already exists{}",registerUserDto.getEmail());
        throw new ResourceAlreadyPresent("User","email",registerUserDto.getEmail());
        }
        else{
            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            Address savedAddress = addressRepository.save(addressService.mapToEntity(registerUserDto.getAddress()));
            logger.info("Saved Address Object : "+new ObjectMapper().writeValueAsString(savedAddress));
            user.setAddress(savedAddress);
            User newUser = userRepository.save(user);
            logger.info("Saved User Object : "+new ObjectMapper().writeValueAsString(newUser));
            RegisterUserDto response = mapToDto(newUser);
            response.setAddress(addressService.mapToDto(savedAddress));
            logger.info("total time taken in saving User{}",(endTime-startTime));
            return response;
        }
    }
    private RegisterUserDto mapToDto(User user){
        RegisterUserDto registerUserDto = mapper.map(user,RegisterUserDto.class);
        return registerUserDto;
    }

    private User mapToEntity(RegisterUserDto registerUserDto){
        User user=mapper.map(registerUserDto,User.class);
        return user;
    }
}
