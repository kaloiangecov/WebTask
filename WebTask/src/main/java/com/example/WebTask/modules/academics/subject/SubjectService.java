package com.example.WebTask.modules.academics.subject;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) throws Exception{
        Subject subject = validateId(id);
        return subject;
    }

    public Subject create(Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject update(Subject subject, Long id) throws Exception{
        Subject dbSubject = validateId(id);

        dbSubject.setCode(subject.getCode());
        dbSubject.setDescription(subject.getDescription());
        dbSubject.setName(subject.getName());
        dbSubject.setCredits(subject.getCredits());
//        dbSubject.setDisciplines(subject.getDisciplines());

        return subjectRepository.save(dbSubject);
    }

    public Subject delete(Long id) throws Exception{
        Subject subject = validateId(id);
        subjectRepository.delete(subject);
        return subject;
    }


    private Subject validateId(Long id) throws Exception {
        Subject faculty = subjectRepository.findOne(id);
        if(faculty != null) {
            return faculty;
        }
        throw new Exception();
    }

    public Page<Subject> getSubjectsPage(int page, int elementsPerPage, String searchTerm) {
        PageRequest pageRequest = new PageRequest(page, elementsPerPage);
        Predicate predicate = SubjectPredicates.searchFields(searchTerm);
        return subjectRepository.findAll(predicate, pageRequest);
    }
}
