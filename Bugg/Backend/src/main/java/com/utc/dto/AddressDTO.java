package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.List;

@Data
public class AddressDTO {

    @JsonFormat(pattern = "AddressID")
    private int id;

    @JsonFormat(pattern = "AddressCountry")
    private String country;

    @JsonFormat(pattern = "AddressCity")
    private String city;

    private List<GuestsDTO> guests;

}
