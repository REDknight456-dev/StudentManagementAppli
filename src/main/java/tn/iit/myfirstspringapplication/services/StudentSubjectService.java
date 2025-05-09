package tn.iit.myfirstspringapplication.services;

import jakarta.persistence.Id;
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
    private final StudentService studentService;


    public StudentSubjectService(StudentSubjectRepository subjectRepository, StudentRepository studentRepository, StudentService studentService) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
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

    public void deleteSubjectById(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        }
    }
    public StudentSubject updateSubject(Long subjectId, StudentSubject updatedSubject) {
        Optional<StudentSubject> existingSubjectOpt = subjectRepository.findById(subjectId);
        if (existingSubjectOpt.isPresent()) {
            StudentSubject existingSubject = existingSubjectOpt.get();
            existingSubject.setName(updatedSubject.getName());
            existingSubject.setMark(updatedSubject.getMark());

            StudentSubject savedSubject = subjectRepository.save(existingSubject);
            recalculateStudentAverage(savedSubject.getStudent());
            return savedSubject;
        } else {
            return null;
        }
    }


    private void recalculateStudentAverage(Student student) {
        List<StudentSubject> subjects = getAllSubjects(); // FIXED

        if (!subjects.isEmpty()) {
            double totalWeightedMarks = subjects.stream()
                    .mapToDouble(s -> s.getMark() * s.getCoefficient())
                    .sum();

            double totalCoefficients = subjects.stream()
                    .mapToDouble(StudentSubject::getCoefficient)
                    .sum();

            double average = totalCoefficients != 0 ? totalWeightedMarks / totalCoefficients : 0.0;

            student.setAverage(average);
            studentRepository.save(student);
        }
    }

}



