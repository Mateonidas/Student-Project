package com.mpolec.student.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_faculty", referencedColumnName = "id")
    private FacultyEntity faculty;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "userid")
    private UserEntity user;
}
