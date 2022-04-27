package com.utc.repository;

import com.utc.entity.StarRate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStarRateRepository extends JpaRepository<StarRate,Integer>{
}
