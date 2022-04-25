package com.vti.finalexam.service;

import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.entiy.Department;
import com.vti.finalexam.form.DepartmentFilter;
import com.vti.finalexam.form.insert.DepartmentCreate;
import com.vti.finalexam.form.update.DepartmentUpdate;
import com.vti.finalexam.repository.IAccountRepository;
import com.vti.finalexam.repository.IDepartmentRepository;
import com.vti.finalexam.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService implements IDepartmentService{

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Department> findListAccount() {
        return departmentRepository.findAll();
    }

    @Override
    public Page<Department> findListDepartmentPaging(String search, Pageable pageable, DepartmentFilter filter) {
        Specification<Department> where = DepartmentSpecification.buildWhere(search,filter);
        return departmentRepository.findAll(where,pageable);
    }

    @Override
    public void createDepartment(DepartmentCreate form) {
        Department department = new Department(form.getName(),Department.Type.toEnum(form.getType()));
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(int id,DepartmentUpdate form) {
        Department department = departmentRepository.findById(id).get();
        department.setType(Department.Type.toEnum(form.getType()));
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void deleteAllDepartment(List<Integer> ids) {
        departmentRepository.deleteAllDepartment(ids);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }
}
