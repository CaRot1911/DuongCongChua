package com.utc.repository;

import com.utc.entity.Guests;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IGuestsRepository extends JpaRepository<Guests,Integer>, JpaSpecificationExecutor<Guests> {

}
