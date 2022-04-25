package com.vti.finalexam.controller;

import com.vti.finalexam.dto.DepartmentDTO;
import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.entiy.Department;
import com.vti.finalexam.form.DepartmentFilter;
import com.vti.finalexam.form.insert.DepartmentCreate;
import com.vti.finalexam.form.update.DepartmentUpdate;
import com.vti.finalexam.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/departments/v1")
@CrossOrigin("http://127.0.0.1:5501/")
@Validated
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllDepartment(){
        List<Department> departmentList = service.findListAccount();
        List<DepartmentDTO> departmentDTOList = modelMapper.map(departmentList,new TypeToken<List<DepartmentDTO>>(){}.getType());

        return new ResponseEntity<>(departmentDTOList, HttpStatus.OK);
    }


    @GetMapping("/getDepartmentByName")
    public ResponseEntity<?> getDepartmentByName(@RequestParam(value = "name") String name){
        Department department = service.getDepartmentByName(name);
        DepartmentDTO departmentDTO = modelMapper.map(department,DepartmentDTO.class);

        return new ResponseEntity<DepartmentDTO>(departmentDTO,HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<?> findAllDepartmentPaging(@RequestParam(value = "search",required = false) String search, Pageable pageable,DepartmentFilter filter){
        Page<Department> departmentPage = service.findListDepartmentPaging(search,pageable,filter);
        List<DepartmentDTO> departmentDTOList = modelMapper.map(departmentPage.getContent(),new TypeToken<List<DepartmentDTO>>(){}.getType());

        Page<DepartmentDTO> page = new PageImpl<>(departmentDTOList,pageable,departmentPage.getTotalElements());

        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentCreate form){
        service.createDepartment(form);
        return new ResponseEntity<>("Create success!!!",HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @CrossOrigin("http://127.0.0.1:5501/")
    public ResponseEntity<?> updateDepartment(@PathVariable(value = "id") int id, @RequestBody DepartmentUpdate form){
        service.updateDepartment(id, form);
        return new ResponseEntity<>("Update success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(value = "id") int id){
        service.deleteDepartment(id);
        return new ResponseEntity<>("Delete success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllDepartment")
    public ResponseEntity<?> deleteAllDepartment(@RequestParam(value = "ids") List<Integer> ids){
        service.deleteAllDepartment(ids);
        return new ResponseEntity<>("Delete all department success!!!",HttpStatus.OK);
    }

}
