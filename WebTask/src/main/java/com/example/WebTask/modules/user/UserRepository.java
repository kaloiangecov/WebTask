package com.example.WebTask.modules.user;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kaloi
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        QueryDslPredicateExecutor {

    Page<User> findAll(Predicate predicate, Pageable pageRequest);

    User findByUsername(String username);

    User findByEmailContaining(String email);
}
