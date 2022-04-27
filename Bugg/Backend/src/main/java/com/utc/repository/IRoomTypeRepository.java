package com.utc.repository;

import com.utc.entity.RoomType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomTypeRepository extends JpaRepository<RoomType,Integer> {
}
