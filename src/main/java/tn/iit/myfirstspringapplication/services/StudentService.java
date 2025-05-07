package tn.iit.myfirstspringapplication.services;

import org.springframework.stereotype.Service;
import tn.iit.myfirstspringapplication.DTO.StudentCreateDTO;
import tn.iit.myfirstspringapplication.DTO.StudentUpdateDTO;
import tn.iit.myfirstspringapplication.models.Student;
import tn.iit.myfirstspringapplication.repositories.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentCreateDTO studentDTO){
        //This is a new comment
        //Test
        Student student = new Student();
        student.setAge(studentDTO.getAge());
        student.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
        return studentRepository.save(student);

    }

    public Student getStudentById(Long id) {

        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        } else {
            throw new RuntimeException("Student with id " + id + " not found");
        }
    }

    public Student updateStudent(Long id, StudentUpdateDTO studentDTO) {
        Student student = getStudentById(id);
        student.setAge(studentDTO.getAge());
        student.setAverage(studentDTO.getAverage());
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student with id " + id + " not found");
        }
    }

}
