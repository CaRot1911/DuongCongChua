package com.vti.finalexam.repository;

import com.vti.finalexam.entiy.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

    @Query(value = "SELECT d FROM Department d WHERE d.name LIKE :name")
    public Department getDepartmentByName(@Param(value = "name") String name);

    public Department findDepartmentById(int id);


    @Query(value = "DELETE FROM Department d WHERE d.id IN (:ids)")
    @Modifying
    @Transactional
    public void deleteAllDepartment(@Param(value = "ids") List<Integer> ids);
}
