package com.mpolec.student.project.controller;

import com.mpolec.student.project.entity.FacultyEntity;
import com.mpolec.student.project.entity.StudentEntity;
import com.mpolec.student.project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/list")
    public String listFaculties(Model model){

        List<FacultyEntity> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);

        return "faculty/list-faculties";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        FacultyEntity faculty = new FacultyEntity();
        model.addAttribute("faculty", faculty);

        return "faculty/faculty-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("facultyId") int id, Model model){

        FacultyEntity faculty = facultyService.findById(id);
        model.addAttribute("faculty", faculty);

        return "faculty/faculty-form";
    }

    @GetMapping("/showStudentList")
    public String showStudentList(@RequestParam("facultyId") int id, Model model){

        FacultyEntity faculty = facultyService.findById(id);
        model.addAttribute("faculty", faculty);
        model.addAttribute("students", faculty.getStudents());

        return "faculty/list-faculty-students";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("facultyId") int id){
        facultyService.deleteById(id);

        return "redirect:/faculties/list";
    }

    @PostMapping("/save")
    public String saveFaculty(@Valid @ModelAttribute("faculty") FacultyEntity faculty, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "faculty/faculty-form";
        }

        facultyService.save(faculty);

        return "redirect:/faculties/list";
    }
}
