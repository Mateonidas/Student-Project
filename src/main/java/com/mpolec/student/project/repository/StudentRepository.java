package com.mpolec.student.project.repository;

import com.mpolec.student.project.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    List<StudentEntity> findAllByOrderByLastNameAsc();

}
