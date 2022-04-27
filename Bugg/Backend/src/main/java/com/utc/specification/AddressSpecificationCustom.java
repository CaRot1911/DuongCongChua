package com.utc.specification;

import com.utc.entity.Address;
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
public class AddressSpecificationCustom implements Specification<Address> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("id")){
            return criteriaBuilder.equal(root.get("id"),value);
        }

        if(filter.equalsIgnoreCase("country")){
            return criteriaBuilder.like(root.get("country"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("city")){
            return criteriaBuilder.like(root.get("city"),"%" + value.toString() + "%");
        }
        return null;
    }
}
