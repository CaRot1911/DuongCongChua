package com.vti.finalexam.service;

import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.form.AccountFilter;
import com.vti.finalexam.form.insert.AccountCreate;
import com.vti.finalexam.form.update.AccountUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {

    public List<Account> findListAccount();
    public Page<Account> findListAccountPaging(String search, Pageable pageable, AccountFilter filter);
    public void createAccount(AccountCreate create);
    public void updateAccount(int id,AccountUpdate update);
    public void deleteAccount(int id);
    public void deleteAllAccount(List<Integer> ids);
    public void createAccountSignUp(Account account);
    public List<Account> getAccountByDepartmentIdIsNull();
    public void updateAllAccounts(int departmentId,List<Integer> idu);
}
