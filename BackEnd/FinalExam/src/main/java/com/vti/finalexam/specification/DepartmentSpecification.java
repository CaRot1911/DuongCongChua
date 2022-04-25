package com.vti.finalexam.specification;

import com.vti.finalexam.entiy.Department;
import com.vti.finalexam.form.DepartmentFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecification {

    public static Specification<Department> buildWhere(String search, DepartmentFilter filter) {
        Specification<Department> where = null;
        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            DepartmentSpecificationCustom name = new DepartmentSpecificationCustom("name", search);
            where = Specification.where(name);
        }

        if (filter != null &&  filter.getMinCreateDate() != null) {
            DepartmentSpecificationCustom minDate = new DepartmentSpecificationCustom("minCreateDate", filter.getMinCreateDate());
            System.out.println(minDate);
            if (where == null) {
                where = minDate;
            } else {
                where = where.and(minDate);
            }
        }

        if (filter != null && filter.getMaxCreateDate() != null) {
            DepartmentSpecificationCustom maxDate = new DepartmentSpecificationCustom("maxCreateDate", filter.getMaxCreateDate());
            if (where == null) {
                where = maxDate;
            } else {
                where = where.and(maxDate);
            }
        }

        if (filter != null && !StringUtils.isEmpty(filter.getType())) {
            DepartmentSpecificationCustom type = new DepartmentSpecificationCustom("type", filter.getType());
            if (where == null) {
                where = type;
            } else {
                where = where.and(type);
            }
        }
        return where;
    }
}
