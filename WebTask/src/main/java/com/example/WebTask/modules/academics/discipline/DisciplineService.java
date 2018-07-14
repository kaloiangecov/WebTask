package com.example.WebTask.modules.academics.discipline;

import com.example.WebTask.modules.academics.subject.Subject;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DisciplineService {

    private DisciplineRepository disciplineRepository;
    private DisciplineSubjectRepository disciplineSubjectRepository;

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository,
                             DisciplineSubjectRepository disciplineSubjectRepository) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineSubjectRepository = disciplineSubjectRepository;
    }


    public List<DisciplineDTO> findAll() {
        return convertToDto(disciplineRepository.findAll());
    }

    public DisciplineDTO findById(Long id) throws Exception{
        Discipline discipline = validateId(id);
        return convertToDto(discipline);
    }

    public DisciplineDTO create(DisciplineDTO disciplineDTO) {
        return convertToDto(disciplineRepository.save(convertFromDto(disciplineDTO)));
    }

    public DisciplineDTO update(DisciplineDTO disciplineDTO, Long id) throws Exception{
        return convertToDto(convertFromDto(disciplineDTO));
    }

    public Discipline delete(Long id) throws Exception{
        Discipline discipline = validateId(id);
        disciplineRepository.delete(discipline);
        return discipline;
    }


    private Discipline validateId(Long id) throws Exception {
        Discipline discipline = disciplineRepository.findOne(id);
        if(discipline != null) {
            return discipline;
        }
        throw new Exception();
    }

    public Page<Discipline> getDisciplinesPage(int page, int elementsPerPage, String searchTerm) {
        PageRequest pageRequest = new PageRequest(page, elementsPerPage);
        Predicate predicate = DisciplinePredicates.searchFields(searchTerm);
        return disciplineRepository.findAll(predicate, pageRequest);
    }

    private DisciplineDTO convertToDto(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setDescription(discipline.getDescription());
        disciplineDTO.setCode(discipline.getCode());
        disciplineDTO.setDepartment(discipline.getDepartment());
        disciplineDTO.setEducationDegree(discipline.getEducationDegree());
        disciplineDTO.setEducationType(discipline.getEducationType());
        disciplineDTO.setNumberOfSemesters(discipline.getNumberOfSemesters());
        disciplineDTO.convertSubjects(discipline.getDisciplineSubjects());

        return disciplineDTO;
    }
    private List<DisciplineDTO> convertToDto(List<Discipline> disciplines) {
        List<DisciplineDTO> disciplineDTOs = new ArrayList<DisciplineDTO>();
        for (Discipline discipline: disciplines) {
            DisciplineDTO disciplineDTO = new DisciplineDTO();
            disciplineDTO.setId(discipline.getId());
            disciplineDTO.setName(discipline.getName());
            disciplineDTO.setDescription(discipline.getDescription());
            disciplineDTO.setCode(discipline.getCode());
            disciplineDTO.setDepartment(discipline.getDepartment());
            disciplineDTO.setEducationDegree(discipline.getEducationDegree());
            disciplineDTO.setEducationType(discipline.getEducationType());
            disciplineDTO.setNumberOfSemesters(discipline.getNumberOfSemesters());
            disciplineDTO.convertSubjects(discipline.getDisciplineSubjects());
            disciplineDTOs.add(disciplineDTO);
        }

        return disciplineDTOs;
    }
    private Discipline convertFromDto(DisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();
        if (disciplineDTO.getId() != null) {
            discipline = disciplineRepository.findOne(disciplineDTO.getId());
            discipline.setDisciplineSubjects(disciplineDTO.getDisciplineSubjects(discipline));
            removeUnreferencedLinks(discipline);
        } else {
            discipline.setDisciplineSubjects(disciplineDTO.getDisciplineSubjects(discipline));
        }
        discipline.setDepartment(disciplineDTO.getDepartment());
        discipline.setEducationDegree(disciplineDTO.getEducationDegree());
        discipline.setEducationType(disciplineDTO.getEducationType());
        discipline.setNumberOfSemesters(disciplineDTO.getNumberOfSemesters());
        discipline.setCode(disciplineDTO.getCode());
        discipline.setName(discipline.getName());
        discipline.setDescription(disciplineDTO.getDescription());

        return discipline;
    }

    private void removeUnreferencedLinks(Discipline discipline) {
        Set<DisciplineSubject> disciplineSubjects =
                disciplineSubjectRepository.findAllByPrimaryKeyDisciplineCode(discipline.getCode());
        disciplineRepository.save(discipline);

        for (DisciplineSubject dbSubject: disciplineSubjects) {
            if (!discipline.getDisciplineSubjects().contains(dbSubject)) {
                disciplineSubjectRepository.delete(dbSubject.getPrimaryKey());
            }
        }
    }
}
