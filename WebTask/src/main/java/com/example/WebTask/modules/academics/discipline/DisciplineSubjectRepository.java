package com.example.WebTask.modules.academics.discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DisciplineSubjectRepository extends JpaRepository<DisciplineSubject, DisciplineSubjectId> {

    Set<DisciplineSubject> findAllByPrimaryKeyDisciplineCode(String code);
}
