package com.mpolec.student.project.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@Entity
@Table(name = "faculty")
public class FacultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Must not be blank")
    @Column(name = "shortcut")
    private String shortcut;

    @Pattern(regexp = "[a-zA-Z]{2,60}", message="Please enter a valid name")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Must not be blank")
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "faculty")
    private Collection<StudentEntity> students;

}
