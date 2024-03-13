package com.example.api.student.service;

import com.example.api.student.model.Student;
import com.example.api.student.model.dto.StudentDto;
import com.example.api.student.model.dto.StudentFilter;

import java.util.List;

public interface IStudentService {
    List<Student> findAll(StudentFilter filter);

    Student findById(Long id);

    Student save(StudentDto student);

    void delete(Long id);
}
