package com.example.WebTask.modules.academics.faculty;

import com.example.WebTask.modules.academics.Academics;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@PrimaryKeyJoinColumn(name = "FACULTY_ID")
public class Faculty extends Academics {

}
