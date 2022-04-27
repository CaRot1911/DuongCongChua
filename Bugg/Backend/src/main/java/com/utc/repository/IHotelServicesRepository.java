package com.utc.repository;

import com.utc.entity.HotelServices;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHotelServicesRepository extends JpaRepository<HotelServices,Integer>{
}
