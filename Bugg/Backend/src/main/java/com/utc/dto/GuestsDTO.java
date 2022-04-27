package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Data
public class GuestsDTO {

    @JsonFormat(pattern = "ID Card")
    private String idCard;

    @JsonFormat(pattern = "Full Name")
    private String fullName;

    @JsonFormat(pattern = "Country")
    private String addressCountry;

    @JsonFormat(pattern = "City")
    private String addressCity;

    @JsonFormat(pattern = "Type")
    private String userTypeName;
}
