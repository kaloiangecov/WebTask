package com.example.WebTask.modules.academics.subject;

import com.example.WebTask.modules.academics.Academics;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class SubjectDTO extends Academics {

    private Long id;
    @NotBlank(message = "Enter credits")
    private String credits;
    private int semester;

    public Subject convertToSubject() {
        Subject subject = new Subject();
        subject.setCredits(this.credits);
        subject.setId(this.id);
        subject.setCode(this.getCode());
        subject.setDescription(this.getDescription());
        subject.setName(this.getName());
        return subject;
    }
}
