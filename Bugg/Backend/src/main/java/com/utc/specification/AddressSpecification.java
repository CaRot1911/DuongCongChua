package com.utc.specification;

import com.utc.entity.Address;
import com.utc.form.filter.AddressFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AddressSpecification {



    public static Specification<Address> buildWhere(Integer search, AddressFilter filter){
        Specification<Address> where = null;


        if(!StringUtils.isEmpty(search)){
            AddressSpecificationCustom id = new AddressSpecificationCustom("id",search);
            where = Specification.where(id);
        }

        if (filter != null && filter.getCountry() != null){
            AddressSpecificationCustom country = new AddressSpecificationCustom("country",filter.getCountry());
            if (where == null){
                where = country;
            }else {
                where = where.and(country);
            }
        }
        if (filter != null && filter.getCity() != null){
            AddressSpecificationCustom city = new AddressSpecificationCustom("city",filter.getCity());
            if (where == null){
                where = city;
            }else {
                where = where.and(city);
            }
        }
        return where;
    }
}
