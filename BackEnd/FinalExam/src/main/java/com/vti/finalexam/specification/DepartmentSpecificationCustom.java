package com.vti.finalexam.specification;

import com.vti.finalexam.entiy.Department;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Data
public class DepartmentSpecificationCustom implements Specification<Department> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("name")){
            return criteriaBuilder.like(root.get("name"),"%" + value + "%");
        }
        else if(filter.equalsIgnoreCase("minCreateDate")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate").as(java.util.Date.class),(Date) value);
        }
//        plus one day
        else if(filter.equalsIgnoreCase("maxCreateDate")){
            Date now = (Date) value;
            Date tomorrow = new Date(now.getTime() + (1000 *60*60*24));
            return criteriaBuilder.lessThan(root.get("createDate").as(java.util.Date.class),(Date) tomorrow);
        }
        else if(filter.equalsIgnoreCase("type")){
            return criteriaBuilder.equal(root.get("type"),Department.Type.toEnum(value.toString()));
        }
        return null;
    }
}
