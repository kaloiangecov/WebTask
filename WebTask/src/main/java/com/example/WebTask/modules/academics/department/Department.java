package com.example.WebTask.modules.academics.department;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.faculty.Faculty;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@PrimaryKeyJoinColumn(name = "DEPARTMENT_ID")
public class Department extends Academics {



    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "FACULTY_ID", nullable = false)
    private Faculty faculty;
}
