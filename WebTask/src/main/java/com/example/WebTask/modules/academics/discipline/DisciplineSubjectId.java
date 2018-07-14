package com.example.WebTask.modules.academics.discipline;

import com.example.WebTask.modules.academics.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class DisciplineSubjectId implements Serializable {

    @ManyToOne
    @JsonIgnore
    private Discipline discipline;
    @ManyToOne
    private Subject subject;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisciplineSubjectId that = (DisciplineSubjectId) o;

        if (discipline != null ? !discipline.equals(that.discipline) : that.discipline != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
