package com.validation.assignment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.validation.assignment.dto.AddressDto;
import com.validation.assignment.dto.RegisterUserDto;
import com.validation.assignment.entity.Address;
import com.validation.assignment.entity.User;
import com.validation.assignment.repository.UserRepository;
import com.validation.assignment.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private AddressServiceImpl addressService;

    @Test
    void registerUserTest() throws JsonProcessingException {
        //Arrange
        User user1 = User.builder().id(1)
                .fullName("Shubham")
                .email("shubham@gmail.com")
                .dateOfBirth(new Date("09/03/1996"))
                .gender("Male")
                .salary(1200)
                .address(Address.builder().id(1)
                        .country("India")
                        .city("Kanpur")
                        .zipCode("208012")
                        .street("P.road")
                        .state("Uttar Pradesh")
                        .build())
                .build();
        BDDMockito.given(userRepository.findAll()).willReturn(List.of(user1));

        RegisterUserDto request = RegisterUserDto.builder().fullName("Noopur Agarwal")
                .email("Nupur@gmail.com")
                .dateOfBirth(new Date("22/07/1991"))
                .gender("female")
                .salary(1500)
                .address(AddressDto.builder()
                        .country("India")
                        .city("Lucknow")
                        .zipCode("208012")
                        .street("P.road")
                        .state("Uttar Pradesh")
                        .build())
                .build();

        //Act
        RegisterUserDto response = userService.registerUser(request);

        //assert
        assertThat(response.getSalary()==1500);
        assertThat(userRepository.findAll().size()==2);

    }

//    @Test
//    void registerUserExceptionTest() {
//        RegisterUserDto request = RegisterUserDto.builder().fullName("Noopur Agarwal")
//                .email("Nupur@gmail.com")
//                .dateOfBirth(new Date("22/07/1991"))
//                .gender("female")
//                .salary(-200)
//                .address(AddressDto.builder()
//                        .country("")
//                        .city("Lucknow")
//                        .zipCode("208012")
//                        .street("P.road")
//                        .state("Uttar Pradesh")
//                        .build())
//                .build();
//        MethodArgumentNotValidException ex = assertThrows(MethodArgumentNotValidException.class,()->userService.registerUser(request));
//        assertTrue(ex.getMessage().contains("salary must be greater than zero"));
//    }
}