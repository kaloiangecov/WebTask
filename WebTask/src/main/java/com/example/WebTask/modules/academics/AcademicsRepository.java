package com.example.WebTask.modules.academics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicsRepository extends JpaRepository<Academics, Long> {

    Academics findByCode(String code);
}
