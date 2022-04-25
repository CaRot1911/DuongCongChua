package com.vti.finalexam.form.insert;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentCreate {

    @NonNull
    @NotBlank(message = "Name can't be a blank")
    @NotNull(message = "Name can't null")
    private String name;

    @NonNull
    @NotBlank(message = "Type can't be a blank")
    @NotNull(message = "Type can't null")
    private String type;

}
