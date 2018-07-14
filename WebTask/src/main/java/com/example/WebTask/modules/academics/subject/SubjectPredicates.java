package com.example.WebTask.modules.academics.subject;

import com.querydsl.core.types.Predicate;

final class SubjectPredicates {

    private SubjectPredicates() {}

    static Predicate searchFields(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return QSubject.subject.isNotNull();
        }
        else {
            return  QSubject.subject.name.containsIgnoreCase(searchTerm)
                    .or(QSubject.subject.code.containsIgnoreCase(searchTerm))
                    .or(QSubject.subject.credits.containsIgnoreCase(searchTerm));
        }
    }
}
