package com.example.WebTask.modules.academics.discipline;

import com.querydsl.core.types.Predicate;

final class DisciplinePredicates {

    private DisciplinePredicates() {}

    static Predicate searchFields(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return QDiscipline.discipline.isNotNull();
        }
        else {
            return  QDiscipline.discipline.name.containsIgnoreCase(searchTerm)
                    .or(QDiscipline.discipline.code.containsIgnoreCase(searchTerm))
                    .or(QDiscipline.discipline.department.name.containsIgnoreCase(searchTerm));
        }
    }
}
