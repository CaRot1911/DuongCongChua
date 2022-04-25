package com.vti.finalexam.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignInDTO {

    @NonNull
    @NotBlank
    @NotNull(message = "User Name khong duoc rong")
    private String userName;

    @NonNull
    @NotBlank
    @NotNull(message = "Password khong duoc rong")
    private String passWord;
}
