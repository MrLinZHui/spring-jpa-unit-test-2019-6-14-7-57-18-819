package com.oocl.web.sampleWebApp.jpaSample.repository;

import javax.persistence.*;

@Entity
@Table(name = "T_SingleEntity")
public class SingleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name",length = 64,nullable = false)
    private String name;
    @Column(name = "age")
    private int age;
    public SingleEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
