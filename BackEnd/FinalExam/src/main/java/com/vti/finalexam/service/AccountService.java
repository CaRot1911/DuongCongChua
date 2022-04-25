package com.vti.finalexam.service;

import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.entiy.Department;
import com.vti.finalexam.form.AccountFilter;
import com.vti.finalexam.form.insert.AccountCreate;
import com.vti.finalexam.form.update.AccountUpdate;
import com.vti.finalexam.repository.IAccountRepository;
import com.vti.finalexam.repository.IDepartmentRepository;
import com.vti.finalexam.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Account> findListAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> findListAccountPaging(String search, Pageable pageable, AccountFilter filter) {
        Specification<Account> where = AccountSpecification.buildWhere(search, filter);
        return accountRepository.findAll(where,pageable);
    }

    @Override
    public void createAccount(AccountCreate form) {
        Department department = departmentRepository.findById(form.getDepartmentId()).get();
        Account account = new Account();
        account.setUserName(form.getUserName());
        account.setFirstName(form.getFirstName());
        account.setLastName(form.getLastName());
        account.setPassWord(form.getPassWord());
        account.setRole(Account.Status.toEnum(form.getRole()));
        account.setDepartment(department);
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(int id,AccountUpdate form) {
        Account account = accountRepository.findById(id).get();
        account.setRole(Account.Status.toEnum(form.getRole()));
        Department department = departmentRepository.findDepartmentById(form.getDepartmentId());
        account.setDepartment(department);

        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAllAccount(List<Integer> ids) {
        accountRepository.deleteAllAccount(ids);
    }

    @Override
    public List<Account> getAccountByDepartmentIdIsNull() {
        return accountRepository.getAccountByDepartmentIdIsNull();
    }

    @Override
    public void updateAllAccounts(int departmentId, List<Integer> idu) {
        accountRepository.updateAllAccounts(departmentId,idu);
    }


    @Override
    public void createAccountSignUp(Account account) {
        accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUserName(username);
        if (account == null){
            throw new UsernameNotFoundException(username);
        }

        if (account.getRole() != null){
            return new User(
                    account.getUserName(),
                    account.getPassWord(),
                    AuthorityUtils.createAuthorityList(account.getRole().toString()));
        }else {
            return new User(
                    account.getUserName(),
                    account.getPassWord(),
                    AuthorityUtils.createAuthorityList("EMPLOYEE"));
        }

    }
}
