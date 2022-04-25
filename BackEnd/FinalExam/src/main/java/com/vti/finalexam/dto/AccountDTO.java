package com.vti.finalexam.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AccountDTO {


    private int id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String userName;

    private String role;

    private int departmentId;

    private String departmentName;

}
