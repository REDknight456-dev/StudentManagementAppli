package tn.iit.myfirstspringapplication.services;

import org.springframework.stereotype.Service;
import tn.iit.myfirstspringapplication.models.Student;
import tn.iit.myfirstspringapplication.models.StudentSubject;
import tn.iit.myfirstspringapplication.repositories.StudentRepository;
import tn.iit.myfirstspringapplication.repositories.StudentSubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSubjectService {

    private final StudentSubjectRepository subjectRepository;
    private final StudentRepository studentRepository;


    public StudentSubjectService(StudentSubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public StudentSubject createSubject(Long studentId, StudentSubject subject){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            Student studentObj = student.get();
            subject.setStudent(studentObj);
            return subjectRepository.save(subject);
        } else {
            return null;
        }
    }

    public List<StudentSubject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public StudentSubject updateSubject(Long subjectId, StudentSubject updatedSubject) {
        Optional<StudentSubject> existingSubjectOpt = subjectRepository.findById(subjectId);
        if (existingSubjectOpt.isPresent()) {
            StudentSubject existingSubject = existingSubjectOpt.get();

            existingSubject.setName(updatedSubject.getName());
            existingSubject.setGrade(updatedSubject.getGrade());

            Student student = existingSubject.getStudent();
            StudentSubject savedSubject = subjectRepository.save(existingSubject);

            // Recalculate average
            recalculateStudentAverage(student);

            return savedSubject;
        } else {
            return null; // Or throw a ResourceNotFoundException
        }
    }


    private void recalculateStudentAverage(Student student) {
        List<StudentSubject> subjects = subjectRepository.findByStudentId(student.getId());

        double average = subjects.stream()
                .mapToDouble(StudentSubject::getGrade)
                .average()
                .orElse(0.0);

        student.setAverageGrade(average);
        studentRepository.save(student);
    }








}


