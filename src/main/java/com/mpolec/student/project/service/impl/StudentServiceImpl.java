package com.mpolec.student.project.service.impl;

import com.mpolec.student.project.entity.StudentEntity;
import com.mpolec.student.project.repository.StudentRepository;
import com.mpolec.student.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> findAll() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public StudentEntity findById(int id) {
        Optional<StudentEntity> result = studentRepository.findById(id);

        StudentEntity student;

        if(result.isPresent()){
            student = result.get();
        }
        else {
            throw new RuntimeException("Did not find student id - " + id);
        }

        return student;
    }

    @Override
    public void save(StudentEntity student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
