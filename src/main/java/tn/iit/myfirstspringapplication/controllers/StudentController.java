package tn.iit.myfirstspringapplication.controllers;

import org.springframework.web.bind.annotation.*;
import tn.iit.myfirstspringapplication.DTO.StudentCreateDTO;
import tn.iit.myfirstspringapplication.DTO.StudentUpdateDTO;
import tn.iit.myfirstspringapplication.models.Student;
import tn.iit.myfirstspringapplication.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public Student createStudent(@RequestBody StudentCreateDTO student){
        return studentService.createStudent(student);
    }
    @GetMapping("/students/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }


    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "Student with ID " + id + " deleted successfully.";
    }


}
