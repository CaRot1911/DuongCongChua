package com.vti.finalexam.specification;


import com.vti.finalexam.entiy.Account;
import com.vti.finalexam.form.AccountFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountSpecification {

    public static Specification<Account> buildWhere(String search, AccountFilter filter){
        Specification<Account> where = null;

        if(!StringUtils.isEmpty(search)){
            search = search.trim();

            AccountSpecificationCustom useName = new AccountSpecificationCustom("userName",search);
            AccountSpecificationCustom firstName = new AccountSpecificationCustom("firstName",search);
            AccountSpecificationCustom lastName = new AccountSpecificationCustom("lastName",search);

            where = Specification.where(useName).or(firstName).or(lastName);
        }

        if (filter!= null && !StringUtils.isEmpty(filter.getRole())){
            AccountSpecificationCustom role = new AccountSpecificationCustom("role",filter.getRole());
            if (where == null){
                where = role;
            }else {
                where = where.and(role);
            }
        }

        if (filter!= null && filter.getDepartmentId() != 0){
            AccountSpecificationCustom departmentId = new AccountSpecificationCustom("departmentId",filter.getDepartmentId());
            if (where == null){
                where = departmentId;
            }else {
                where = where.and(departmentId);
            }
        }
        return where;
    }
}
