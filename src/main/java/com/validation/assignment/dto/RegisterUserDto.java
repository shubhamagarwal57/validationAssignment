package com.validation.assignment.dto;
import com.validation.assignment.constraints.BirthDate;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@ApiModel(value="Register user model properties")
public class RegisterUserDto {
    @NotEmpty(message = "The full name is required.")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    private String fullName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotNull(message = "The date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    @BirthDate(message = "User must be at least 10 years of age")
    private Date dateOfBirth;

    @NotEmpty(message = "The gender is required.")
    private String gender;

    @NotNull(message = "Salary cannot be empty")
    @Min(value=1000 ,message="salary must be >1000")
    private int salary;

    @Valid
    @NotNull(message = "The address is required.")
    private AddressDto address;
}
