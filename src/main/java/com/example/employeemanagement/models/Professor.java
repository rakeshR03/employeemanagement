package com.example.employeemanagement.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Professor extends BaseModel{
    private String name;
    private String city;

    @ManyToOne
    private Department department;

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", department=" + department +
                '}';
    }
}
