package com.utc.repository;

import com.utc.entity.RoomBook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomBookRepository extends JpaRepository<RoomBook,Integer> {
}
