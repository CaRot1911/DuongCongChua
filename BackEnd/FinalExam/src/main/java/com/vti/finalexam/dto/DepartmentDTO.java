package com.vti.finalexam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DepartmentDTO {

    private String id;

    private String name;

    private int totalMember;

    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;


}
