package com.mpolec.student.project.controller;

import com.mpolec.student.project.entity.FacultyEntity;
import com.mpolec.student.project.entity.StudentEntity;
import com.mpolec.student.project.entity.UserEntity;
import com.mpolec.student.project.model.StudentModel;
import com.mpolec.student.project.service.FacultyService;
import com.mpolec.student.project.service.StudentService;
import com.mpolec.student.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/list")
    public String listStudents(Model model, Principal principal) {

        UserEntity user = userService.findByLogin(principal.getName());
//        List<StudentEntity> students = user.getStudents();
        model.addAttribute("students", user.getStudents());

        return "student/list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model, HttpSession session) {

        StudentModel student = new StudentModel();
        model.addAttribute("student", student);

        List<FacultyEntity> faculties = facultyService.findAll();
        session.setAttribute("faculties", faculties);

        return "student/student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {

        StudentEntity student = studentService.findById(id);

        List<FacultyEntity> faculties = facultyService.findAll();
        model.addAttribute("faculties", faculties);

        StudentModel studentModel = new StudentModel();
        studentModel.setId(student.getId());
        studentModel.setFirstName(student.getFirstName());
        studentModel.setLastName(student.getLastName());
        studentModel.setEmail(student.getEmail());

        model.addAttribute("student", studentModel);

        return "student/student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int id) {
        studentService.deleteById(id);

        return "redirect:/students/list";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") StudentModel student,
                              BindingResult bindingResult,
                              @ModelAttribute("faculty") FacultyEntity facultyEntity) {

        if(bindingResult.hasErrors()) {
            return "student/student-form";
        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setFaculty(facultyService.findById(student.getFacultyId()));

        studentService.save(studentEntity);

        return "redirect:/students/list";
    }
}
