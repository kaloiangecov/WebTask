package com.example.WebTask.modules.academics.subject;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.discipline.DisciplineSubject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "SUBJECT_ID")
public class Subject extends Academics {

    @NotBlank(message = "Enter credits")
    private String credits;

    @OneToMany(mappedBy = "primaryKey.subject",
               cascade = CascadeType.ALL)
    private Set<DisciplineSubject> disciplineSubjects = new HashSet<>();


    public String getCredits() {
        return credits;
    }
    public void setCredits(String credits) {
        this.credits = credits;
    }

    @JsonIgnore
    public Set<DisciplineSubject> getDisciplineSubjects() {
        return disciplineSubjects;
    }
    public void setDisciplineSubjects(Set<DisciplineSubject> disciplineSubjects) {
        this.disciplineSubjects = disciplineSubjects;
    }

}
