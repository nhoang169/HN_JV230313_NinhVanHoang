package com.example.api.student.controller;

import com.example.api.student.model.Student;
import com.example.api.student.model.dto.StudentDto;
import com.example.api.student.model.dto.StudentFilter;
import com.example.api.student.service.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents(StudentFilter filter) {
        return new ResponseEntity<>(studentService.findAll(filter), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@ModelAttribute StudentDto studentDto) {
        return new ResponseEntity<>(studentService.save(studentDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @ModelAttribute StudentDto studentDto) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (Objects.isNull(studentService.findById(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentDto.setId(id);
        return new ResponseEntity<>(studentService.save(studentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (Objects.isNull(studentService.findById(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
