package com.vti.finalexam.controller;

import com.vti.finalexam.dto.AccountDTO;
import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.form.AccountFilter;
import com.vti.finalexam.form.insert.AccountCreate;
import com.vti.finalexam.form.update.AccountUpdate;
import com.vti.finalexam.service.IAccountService;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("accounts/v1")
@CrossOrigin("http://127.0.0.1:5501/")
@Transactional
@Validated
public class  AccountController {

    @Autowired
    private IAccountService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllAccount(){
        List<Account> lst = service.findListAccount();
        List<AccountDTO> dtos = modelMapper.map(lst,new TypeToken<List<AccountDTO>>(){}.getType());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/accountDepartmentIsNull")
    public ResponseEntity<?> findAccountDepartmentIdIsNull(){
        List<Account> accountList = service.getAccountByDepartmentIdIsNull();
        List<AccountDTO> accountDTOList = modelMapper.map(accountList,new TypeToken<List<AccountDTO>>(){}.getType());

        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }


    @GetMapping("/paging")
    public ResponseEntity<?> findAllAccountPaging(@RequestParam(value = "search",required = false) String search, Pageable pageable,AccountFilter filter){
        System.out.println(search);
        System.out.println(filter.toString());
        Page<Account> accountPage = service.findListAccountPaging(search,pageable,filter);


        List<AccountDTO> accountDTOList = modelMapper.map(accountPage.getContent(),new TypeToken<List<AccountDTO>>(){}.getType());

        Page<AccountDTO> page = new PageImpl<>(accountDTOList,pageable,accountPage.getTotalElements());

        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreate form){
        service.createAccount(form);
        return new ResponseEntity<>("Create success!!!",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(value = "id") int id, @RequestBody AccountUpdate form){
        service.updateAccount(id, form);
        return new ResponseEntity<>("Update success!!!",HttpStatus.OK);
    }

    @PutMapping("/updateAllAccounts")
    public ResponseEntity<?> updateAllAccount(@RequestParam(value = "departmentId") int departmentId,@RequestParam(value = "ids") List<Integer> ids){
        service.updateAllAccounts(departmentId,ids);
        return new ResponseEntity<>("Update All Account Success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://127.0.0.1:5501/")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id") int id){
        service.deleteAccount(id);
        return new ResponseEntity<>("Delete success!!!",HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAccount")
    public ResponseEntity<?> deleteAllAccount(@RequestParam(value = "ids") List<Integer> ids){
        service.deleteAllAccount(ids);
        return new ResponseEntity<>("Delete all account success!!!",HttpStatus.OK);
    }

}
