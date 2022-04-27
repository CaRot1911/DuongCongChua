package com.utc.service;

import com.utc.entity.Hotel;
import com.utc.form.create.HotelCreateForm;
import com.utc.form.filter.HotelFilterForm;
import com.utc.form.update.HotelUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHotelService {

    public List<Hotel> findAllHotel();

    public Page<Hotel> findAllHotelByCity(String city,Pageable pageable);

    public Page<Hotel> findAllHotelByPage(String search, Pageable pageable, HotelFilterForm form);

    public void createHotel(HotelCreateForm form);

    public void updateHotel(int id,HotelUpdateForm form);


}
