package com.example.WebTask.modules.academics.department;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long>,
        QueryDslPredicateExecutor {

    Page<Department> findAll(Predicate predicate, Pageable pageRequest);
}
