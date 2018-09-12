package com.example.WebTask.modules.academics;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Academics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Enter faculty name")
    @Size(min = 4, max = 30, message = "faculty name size must be between 4 and 30 characters")
    private String name;
    @NotBlank(message = "Enter code")
    @Column(unique=true)
    private String code;
    private String description;
}
