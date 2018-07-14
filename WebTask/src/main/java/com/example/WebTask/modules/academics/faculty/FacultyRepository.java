package com.example.WebTask.modules.academics.faculty;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>,
        QueryDslPredicateExecutor {

    Page<Faculty> findAll(Predicate predicate, Pageable pageRequest);
}
