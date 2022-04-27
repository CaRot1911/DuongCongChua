package com.utc.specification;

import com.utc.entity.Hotel;

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
public class HotelSpecificationCustom implements Specification<Hotel> {

    @NonNull
    private String filter;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (filter.equalsIgnoreCase("name")){
            return criteriaBuilder.like(root.get("name"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("addressCity")){
            return criteriaBuilder.like(root.get("address").get("city"),"%" + value.toString() + "%");
        }

        if (filter.equalsIgnoreCase("addressCountry")){
            return criteriaBuilder.like(root.get("address").get("country"),"%" + value.toString() + "%");
        }
        return null;
    }
}
