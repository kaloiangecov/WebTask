package com.example.WebTask.modules.academics.subject;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>,
        QueryDslPredicateExecutor {

    Page<Subject> findAll(Predicate predicate, Pageable pageRequest);
}
