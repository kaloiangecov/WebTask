package com.example.WebTask.modules.academics;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@Data
@MappedSuperclass
public class Academics {

    @NotBlank(message = "Enter faculty name")
    @Size(min = 4, max = 30, message = "faculty name size must be between 4 and 20 characters")
    private String name;
    @NotBlank(message = "Enter code")
    @Column(unique=true)
    private String code;
    private String description;
}
