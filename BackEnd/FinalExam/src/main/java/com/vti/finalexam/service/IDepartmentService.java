package com.vti.finalexam.service;

import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.entiy.Department;
import com.vti.finalexam.form.AccountFilter;
import com.vti.finalexam.form.DepartmentFilter;
import com.vti.finalexam.form.insert.AccountCreate;
import com.vti.finalexam.form.insert.DepartmentCreate;
import com.vti.finalexam.form.update.AccountUpdate;
import com.vti.finalexam.form.update.DepartmentUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {

    public List<Department> findListAccount();
    public Page<Department> findListDepartmentPaging(String search, Pageable pageable, DepartmentFilter filter);
    public void createDepartment(DepartmentCreate create);
    public void updateDepartment(int id,DepartmentUpdate update);
    public void deleteDepartment(int id);
    public void deleteAllDepartment(List<Integer> ids);
    public Department getDepartmentByName(String name);

}
