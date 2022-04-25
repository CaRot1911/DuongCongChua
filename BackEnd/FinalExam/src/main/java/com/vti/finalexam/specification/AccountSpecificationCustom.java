package com.vti.finalexam.specification;

import com.vti.finalexam.entiy.Account;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class AccountSpecificationCustom implements Specification<Account> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filter.equalsIgnoreCase("userName")){
            return criteriaBuilder.like(root.get("userName"),"%"+value+"%");
        }
        else if (filter.equalsIgnoreCase("firstName")){
            return criteriaBuilder.like(root.get("firstName"),"%"+value+"%");
        }
        else if (filter.equalsIgnoreCase("lastName")){
            return criteriaBuilder.like(root.get("lastName"),"%"+value+"%");
        }
        else if (filter.equalsIgnoreCase("role")){
            return criteriaBuilder.equal(root.get("role"),Account.Status.toEnum(value.toString()));
        }
        else if (filter.equalsIgnoreCase("departmentId")){
            return criteriaBuilder.equal(root.get("department").get("id"),value);
        }

        return null;
    }
}
