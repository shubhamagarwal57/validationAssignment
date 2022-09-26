package com.validation.assignment.service.impl;
import com.validation.assignment.dto.RegisterUserDto;
import com.validation.assignment.entity.Address;
import com.validation.assignment.entity.User;
import com.validation.assignment.exception.ResourceAlreadyPresent;
import com.validation.assignment.repository.AddressRepository;
import com.validation.assignment.repository.UserRepository;
import com.validation.assignment.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private ModelMapper mapper;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private AddressServiceImpl addressService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository, AddressRepository addressRepository,AddressServiceImpl addressService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }
    @Override
    public RegisterUserDto registerUser(RegisterUserDto registerUserDto) {
        User user = mapToEntity(registerUserDto);
        if(userRepository.existsByEmail(registerUserDto.getEmail())){
            logger.error("Exception occurred as the mail id already exists{}",registerUserDto.getEmail());
        throw new ResourceAlreadyPresent("User","email",registerUserDto.getEmail());
        }
        else{
            long startTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            Address savedAddress = addressRepository.saveAndFlush(addressService.mapToEntity(registerUserDto.getAddress()));
            user.setAddress(savedAddress);
            User newUser = userRepository.saveAndFlush(user);
            RegisterUserDto response = mapToDto(newUser);
            response.setAddress(addressService.mapToDto(savedAddress));
            logger.info("total time taken in saving show{}",(endTime-startTime));
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
