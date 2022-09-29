package com.validation.assignment.service.impl;

import com.validation.assignment.dto.AddressDto;
import com.validation.assignment.entity.Address;
import com.validation.assignment.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ModelMapper mapper;

    public AddressServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AddressDto mapToDto(Address address){
        AddressDto addressDto = mapper.map(address,AddressDto.class);
        return addressDto;
    }

    public Address mapToEntity(AddressDto addressDto){
        Address address=mapper.map(addressDto,Address.class);
        return address;
    }
}
