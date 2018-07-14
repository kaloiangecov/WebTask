package com.example.WebTask.modules.academics.discipline;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.department.Department;
import com.example.WebTask.modules.academics.subject.Subject;
import com.example.WebTask.modules.academics.subject.SubjectDTO;
import com.example.WebTask.utils.enumerations.EducationDegree;
import com.example.WebTask.utils.enumerations.EducationType;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Data
public class DisciplineDTO extends Academics {

    private Long id;
    private Department department;
    private EducationType educationType;

    @Min(value = 1, message = "Min Value is 1")
    @Max(value = 10, message = "Max value is 10")
    private int numberOfSemesters;
    private EducationDegree educationDegree;
    private Set<SubjectDTO> subjects;

    public void setSubjects(Set<SubjectDTO> subjects) {
        this.subjects = subjects;
    };

    public void convertSubjects(Set<DisciplineSubject> disciplineSubjects) {
        Set<SubjectDTO> subjects = new HashSet<>();
        for (DisciplineSubject subject: disciplineSubjects) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject.getSubject().getId());
            subjectDTO.setCredits(subject.getSubject().getCredits());
            subjectDTO.setSemester(subject.getSemester());
            subjectDTO.setCode(subject.getSubject().getCode());
            subjectDTO.setDescription(subject.getSubject().getDescription());
            subjectDTO.setName(subject.getSubject().getName());

            subjects.add(subjectDTO);
        }
        this.subjects = subjects;
    };

    public Set<DisciplineSubject> getDisciplineSubjects(Discipline discipline) {
        Set<DisciplineSubject> disciplineSubjects = new HashSet<>();
        for (SubjectDTO subjectDTO: this.subjects) {
            DisciplineSubject disciplineSubject = new DisciplineSubject();
            disciplineSubject.setDiscipline(discipline);
            disciplineSubject.setSemester(subjectDTO.getSemester());
            disciplineSubject.setSubject(subjectDTO.convertToSubject());
            disciplineSubjects.add(disciplineSubject);
        }
        return disciplineSubjects;
    }
}
