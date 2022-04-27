package com.utc.repository;

import com.utc.entity.UserServices;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserServicesRepository extends JpaRepository<UserServices,Integer>{
}
