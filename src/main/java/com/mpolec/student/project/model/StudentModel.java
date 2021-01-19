package com.mpolec.student.project.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class StudentModel {

    private int id;

    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Please enter a valid first name")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Please enter a valid last name")
    private String lastName;

    @Pattern(regexp = "^(.+)@(.+)$", message="Please enter a valid email address")
    private String email;

    private int facultyId;
}
