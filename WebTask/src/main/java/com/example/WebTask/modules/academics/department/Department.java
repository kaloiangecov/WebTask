package com.example.WebTask.modules.academics.department;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.faculty.Faculty;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Department extends Academics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "FACULTY_ID", nullable = false)
    private Faculty faculty;
}
