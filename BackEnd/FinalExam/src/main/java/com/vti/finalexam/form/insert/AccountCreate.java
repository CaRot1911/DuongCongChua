package com.vti.finalexam.form.insert;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountCreate {


    @NotBlank(message = "User Name can't be a blank")
    @NotNull(message = "User Name can't null")
    private String userName;

    @NotBlank(message = "First Name can't be a blank")
    @NotNull(message = "First Name can't null")
    private String firstName;

    @NotBlank(message = "Last Name can't be a blank")
    @NotNull(message = "Last Name can't null")
    private String lastName;

    @Length(min = 6,max = 20,message = "Password must has between 6 , 20 character")
    private String passWord;

    @NotNull(message = "Role can't null")
    private String role;

    @NotNull(message = "DepartmentID can't null")
    private int departmentId;
}
