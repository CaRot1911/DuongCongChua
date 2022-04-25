package com.vti.finalexam.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpDTO {

    @NotNull(message = "User name does not null")
    @NotBlank
    private String userName;

    @NotNull(message = "First name does not null")
    @NotBlank
    private String firstName;

    @NotNull(message = "Last name does not null")
    @NotBlank
    private String lastName;

    @Length(min = 6,max = 20,message = "Password must has between 6 , 20 character")
    private String passWord;
}
