package com.example.WebTask.modules.role;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column
    private String name;

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {}
}