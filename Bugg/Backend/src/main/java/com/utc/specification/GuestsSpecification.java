package com.utc.specification;

import com.utc.entity.Guests;
import com.utc.form.filter.GuestsFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class GuestsSpecification {

    public static Specification<Guests> buildWhere(String search, GuestsFilter filter){
        Specification<Guests> where = null;

        if (!StringUtils.isEmpty(search)){
            search = search.trim();

            GuestsSpecificationCustom fullName = new GuestsSpecificationCustom("fullName",search);
            where = Specification.where(fullName);
        }

        if (filter != null && filter.getIdCard() != null){
            GuestsSpecificationCustom idCard = new GuestsSpecificationCustom("idCard",filter.getIdCard());
            if (where == null){
                where = idCard;
            }else {
                where = where.and(idCard);
            }
        }

        return where;
    }
}
