package com.example.WebTask.modules.academics.department;

import com.querydsl.core.types.Predicate;

final class DepartmentPredicates {

    private DepartmentPredicates() {}

    static Predicate searchFields(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return QDepartment.department.isNotNull();
        }
        else {
            return  QDepartment.department.name.containsIgnoreCase(searchTerm)
                    .or(QDepartment.department.code.containsIgnoreCase(searchTerm))
                    .or(QDepartment.department.faculty.name.containsIgnoreCase(searchTerm));
        }
    }
}
