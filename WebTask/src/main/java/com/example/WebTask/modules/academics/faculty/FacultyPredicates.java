package com.example.WebTask.modules.academics.faculty;

import com.querydsl.core.types.Predicate;

final class FacultyPredicates {

    private FacultyPredicates() {}

    static Predicate searchFields(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return QFaculty.faculty.isNotNull();
        }
        else {
            return  QFaculty.faculty.name.containsIgnoreCase(searchTerm)
                    .or(QFaculty.faculty.code.containsIgnoreCase(searchTerm));
        }
    }
}
