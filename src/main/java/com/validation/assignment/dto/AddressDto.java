package com.validation.assignment.dto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value="address model properties")
public class AddressDto {
    @NotBlank(message = "The country is required.")
    private String country;

    @NotBlank(message = "The city is required.")
    private String city;

    @NotBlank(message = "The Zip code is required.")
    @Pattern(regexp = "^[0-9]{6}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "The Zip code must be of 6 digits")
    private String zipCode;

    @NotBlank(message = "The street name is required.")
    private String street;

    private String state;
}

