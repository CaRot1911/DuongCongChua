package com.utc.specification;

import com.utc.entity.Guests;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@RequiredArgsConstructor
public class GuestsSpecificationCustom implements Specification<Guests> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<Guests> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(filter.equalsIgnoreCase("fullName")){
            return criteriaBuilder.like(root.get("fullName"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("idCard")){
            return criteriaBuilder.like(root.get("idCard"),"%" + value.toString() + "%");
        }
        return null;
    }
}
