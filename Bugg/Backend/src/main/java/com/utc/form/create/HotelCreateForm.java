package com.utc.form.create;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class HotelCreateForm {

    private String name;

    private String email;

    private String website;

    private String description;

    private String country;

    private String city;

    private int roomCount;
}
