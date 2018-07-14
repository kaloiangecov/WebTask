package com.example.WebTask.modules.academics.discipline;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long>,
        QueryDslPredicateExecutor {

    Page<Discipline> findAll(Predicate predicate, Pageable pageRequest);
}
