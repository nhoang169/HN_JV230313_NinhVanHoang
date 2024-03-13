package com.example.api.student.repository;

import com.example.api.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s FROM Student s WHERE :studentName IS NULL OR s.studentName = :studentName")
    List<Student> findAllByStudentName(String studentName);
}
