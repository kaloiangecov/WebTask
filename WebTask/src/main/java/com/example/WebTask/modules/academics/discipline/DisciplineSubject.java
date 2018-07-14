package com.example.WebTask.modules.academics.discipline;

import com.example.WebTask.modules.academics.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discipline_subject")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.discipline",
                joinColumns = @JoinColumn(name = "DISCIPLINE_ID")),
        @AssociationOverride(name = "primaryKey.subject",
                joinColumns = @JoinColumn(name = "SUBJECT_ID")) })
public class DisciplineSubject implements Serializable {

    @EmbeddedId
    private DisciplineSubjectId primaryKey = new DisciplineSubjectId();
    private int semester;

    public DisciplineSubjectId getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(DisciplineSubjectId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Transient
    @JsonIgnore
    public Discipline getDiscipline() {
        return getPrimaryKey().getDiscipline();
    }
    public void setDiscipline(Discipline discipline) {
        getPrimaryKey().setDiscipline(discipline);
    }

    @Transient
    public Subject getSubject() {
        return getPrimaryKey().getSubject();
    }
    public void setSubject(Subject subject) {
        getPrimaryKey().setSubject(subject);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DisciplineSubject that = (DisciplineSubject) o;

        if (getPrimaryKey() != null ? !getPrimaryKey().equals(that.getPrimaryKey())
                : that.getPrimaryKey() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
    }
}
