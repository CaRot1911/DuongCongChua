package com.utc.repository;

import com.utc.entity.RoomRateDiscount;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRateDiscountRepository extends JpaRepository<RoomRateDiscount,Integer> {
}
