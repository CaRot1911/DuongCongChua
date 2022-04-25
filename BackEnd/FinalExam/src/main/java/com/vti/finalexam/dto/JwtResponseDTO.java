package com.vti.finalexam.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtResponseDTO {
    @NonNull
    private String token;

    @NonNull
    private String userName;

    @NonNull
    private String role;

}
