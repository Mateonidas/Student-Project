package com.mpolec.student.project.service;

import com.mpolec.student.project.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    List<StudentEntity> findAll();

    StudentEntity findById(int id);

    void save(StudentEntity student);

    void deleteById(int id);
}
