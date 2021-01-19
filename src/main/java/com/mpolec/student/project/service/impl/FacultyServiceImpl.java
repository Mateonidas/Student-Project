package com.mpolec.student.project.service.impl;

import com.mpolec.student.project.entity.FacultyEntity;
import com.mpolec.student.project.repository.FacultyRepository;
import com.mpolec.student.project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<FacultyEntity> findAll() {
        return facultyRepository.findAllByOrderByNameAsc();
    }

    @Override
    public FacultyEntity findById(int id) {
        Optional<FacultyEntity> result = facultyRepository.findById(id);

        FacultyEntity faculty;

        if(result.isPresent()){
            faculty = result.get();
        }
        else {
            throw new RuntimeException("Did not find faculty id - " + id);
        }

        return faculty;
    }

    @Override
    public void save(FacultyEntity faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public void deleteById(int id) {
        facultyRepository.deleteById(id);
    }
}
