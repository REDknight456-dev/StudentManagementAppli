package tn.iit.myfirstspringapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.myfirstspringapplication.models.Student;
import tn.iit.myfirstspringapplication.models.StudentSubject;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
}
