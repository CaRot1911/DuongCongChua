package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class UserTypeDTO {

    @JsonFormat(pattern = "ID")
    private int id;

    @JsonFormat(pattern = "Type")
    private String type;

    private List<GuestsDTO> guests;

}
