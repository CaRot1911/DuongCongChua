package com.utc.specification;

import com.utc.entity.Hotel;
import com.utc.form.filter.HotelFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class HotelSpecification {

    public static Specification<Hotel> buildWhere(String search, HotelFilterForm form){

        Specification<Hotel> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();
            HotelSpecificationCustom name = new HotelSpecificationCustom("name",search);

            where = Specification.where(name);
        }

        if (form != null && form.getAddressCity() != null){
            HotelSpecificationCustom city = new HotelSpecificationCustom("addressCity",form.getAddressCity());
            if(where == null){
                where = city;
            }else {
                where = where.and(city);
            }
        }

        if (form != null && form.getAddressCountry() != null){
            HotelSpecificationCustom country = new HotelSpecificationCustom("addressCountry",form.getAddressCity());
            if(where == null){
                where = country;
            }else {
                where = where.and(country);
            }
        }

        return where;
    }
}
