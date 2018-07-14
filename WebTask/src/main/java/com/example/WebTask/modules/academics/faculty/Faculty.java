package com.example.WebTask.modules.academics.faculty;

import com.example.WebTask.modules.academics.Academics;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Faculty extends Academics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
