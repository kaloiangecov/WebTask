package com.example.WebTask.modules.academics.discipline;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.department.Department;
import com.example.WebTask.utils.enumerations.EducationDegree;
import com.example.WebTask.utils.enumerations.EducationType;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "DISCIPLINE_ID")
public class Discipline extends Academics {


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    // REGULAR or DISTANCE
    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    @Min(value = 1, message = "Min Value is 1")
    @Max(value = 10, message = "Max value is 10")
    private int numberOfSemesters;

    // BACHELOR, MASTERS or DOCTORATE
    @Enumerated(EnumType.STRING)
    private EducationDegree educationDegree;

    @OneToMany(mappedBy = "primaryKey.discipline",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private Set<DisciplineSubject> disciplineSubjects = new HashSet<>();

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public EducationType getEducationType() {
        return educationType;
    }
    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public int getNumberOfSemesters() {
        return numberOfSemesters;
    }
    public void setNumberOfSemesters(int numberOfSemesters) {
        this.numberOfSemesters = numberOfSemesters;
    }

    public EducationDegree getEducationDegree() {
        return educationDegree;
    }
    public void setEducationDegree(EducationDegree educationDegree) {
        this.educationDegree = educationDegree;
    }

    public Set<DisciplineSubject> getDisciplineSubjects() {
        return disciplineSubjects;
    }
    public void setDisciplineSubjects(Set<DisciplineSubject> disciplineSubjects) {
        this.disciplineSubjects = disciplineSubjects;
    }

}
