package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 32306;
        this.name = "SpringBoot";
        this.dateOfCompletion = "27-Jan-2026";
    }

    public void display() {
        System.out.println("Certification ID   : " + id);
        System.out.println("Certification Name : " + name);
        System.out.println("Completed On       : " + dateOfCompletion);
    }
}
