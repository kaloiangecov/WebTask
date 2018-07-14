package com.example.WebTask.modules.academics.faculty;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Faculty findById(Long id) throws Exception{
        Faculty faculty = validateId(id);
        return faculty;
    }

    public Faculty create(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty update(Faculty faculty, Long id) throws Exception{
        Faculty dbFaculty = validateId(id);

        dbFaculty.setCode(faculty.getCode());
        dbFaculty.setDescription(faculty.getDescription());
        dbFaculty.setName(faculty.getName());

        return facultyRepository.save(dbFaculty);
    }

    public Faculty delete(Long id) throws Exception{
        Faculty faculty = validateId(id);
        facultyRepository.delete(faculty);
        return faculty;
    }


    private Faculty validateId(Long id) throws Exception {
        Faculty faculty = facultyRepository.findOne(id);
        if(faculty != null) {
            return faculty;
        }
        throw new Exception();
    }

    public Page<Faculty> getFacultisPage(int page, int elementsPerPage, String searchTerm) {
        PageRequest pageRequest = new PageRequest(page, elementsPerPage);
        Predicate predicate = FacultyPredicates.searchFields(searchTerm);
        return facultyRepository.findAll(predicate, pageRequest);
    }

}
