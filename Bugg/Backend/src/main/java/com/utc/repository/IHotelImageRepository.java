package com.utc.repository;

import com.utc.entity.Hotel;
import com.utc.entity.HotelImage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelImageRepository extends JpaRepository<HotelImage,String> {
}
