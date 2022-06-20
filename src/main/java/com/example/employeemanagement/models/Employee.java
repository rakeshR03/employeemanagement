package com.example.employeemanagement.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee extends BaseModel{

    private String name;
    private String city;
    private Date birthday;

    @ManyToOne
    private Department department;


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                '}';
    }
}
