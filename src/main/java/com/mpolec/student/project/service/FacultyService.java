package com.mpolec.student.project.service;

import com.mpolec.student.project.entity.FacultyEntity;
import com.mpolec.student.project.entity.StudentEntity;

import java.util.List;

public interface FacultyService {

    List<FacultyEntity> findAll();

    FacultyEntity findById(int id);

    void save(FacultyEntity faculty);

    void deleteById(int id);
}
