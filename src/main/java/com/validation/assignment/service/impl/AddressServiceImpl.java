package com.validation.assignment.service.impl;

import com.validation.assignment.dto.AddressDto;
import com.validation.assignment.entity.Address;
import com.validation.assignment.repository.AddressRepository;
import com.validation.assignment.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private ModelMapper mapper;
    private AddressRepository addressRepository;

    public AddressServiceImpl(ModelMapper mapper, AddressRepository addressRepository) {
        this.mapper = mapper;
        this.addressRepository = addressRepository;
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
