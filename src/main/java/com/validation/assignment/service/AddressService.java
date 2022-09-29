package com.validation.assignment.service;

import com.validation.assignment.dto.AddressDto;
import com.validation.assignment.entity.Address;

public interface AddressService {
    public AddressDto mapToDto(Address address);
    public Address mapToEntity(AddressDto addressDto);
}
