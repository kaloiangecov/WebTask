package com.example.WebTask.modules.user;


import com.querydsl.core.types.Predicate;

final class UserPredicates {

    private UserPredicates() {}

    static Predicate searchFields(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return QUser.user.isNotNull();
        }
        else {
            return  QUser.user.username.containsIgnoreCase(searchTerm)
                    .or(QUser.user.email.containsIgnoreCase(searchTerm));
        }
    }

}
