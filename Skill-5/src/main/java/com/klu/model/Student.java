package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String name;
    private String gender;

    // Autowired object
    @Autowired
    private Certification certification;

    public Student() {
        this.id = 32306;
        this.name = "Pranathi";
        this.gender = "Female";
    }

    public void display() {
        System.out.println("Student ID   : " + id);
        System.out.println("Name         : " + name);
        System.out.println("Gender       : " + gender);
        System.out.println("---- Certification Details ----");
        certification.display();
    }
}
