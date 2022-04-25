package com.vti.finalexam.repository;

import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.entiy.Department;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;


@DynamicUpdate
public interface IAccountRepository extends JpaRepository<Account,Integer> , JpaSpecificationExecutor<Account> {

    @Query("SELECT a.id FROM Account a JOIN Department d WHERE a.department.id = d.id")
    public List<Account> getAccountByDepartmentId();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Account a WHERE a.id IN (:ids)")
    public void deleteAllAccount(@Param(value = "ids") List<Integer> ids);

    public Account findAccountByUserName(String userName);

    @Query(value = "SELECT a FROM Account a WHERE a.department.id IS NULL")
    public List<Account> getAccountByDepartmentIdIsNull();

    @Modifying
    @Query(value = "UPDATE Account SET department.id = :departmentId WHERE id IN (:idu)")
    public void updateAllAccounts(@Param(value = "departmentId") int departmentId, @Param(value = "idu") List<Integer> idu);

}