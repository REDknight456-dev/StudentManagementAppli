package tn.iit.myfirstspringapplication.controllers;

import org.springframework.web.bind.annotation.*;
import tn.iit.myfirstspringapplication.models.StudentSubject;
import tn.iit.myfirstspringapplication.services.StudentSubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final StudentSubjectService studentSubjectService;

    public SubjectController(StudentSubjectService studentSubjectService){
        this.studentSubjectService = studentSubjectService;
    }

    @PostMapping("{studentId}")
    public StudentSubject createStudentSubject(@PathVariable Long studentId, @RequestBody StudentSubject subject) {
        return studentSubjectService.createSubject(studentId, subject);
    }

    @GetMapping("")
    public List<StudentSubject> getAllSubjects(){
        return studentSubjectService.getAllSubjects();
    }
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        studentSubjectService.deleteSubjectById(id);
    }
    @PutMapping("/{subjectId}")
    public StudentSubject updateStudentSubject(@PathVariable Long subjectId, @RequestBody StudentSubject subject) {
        return studentSubjectService.updateSubject(subjectId, subject);
    }

}

