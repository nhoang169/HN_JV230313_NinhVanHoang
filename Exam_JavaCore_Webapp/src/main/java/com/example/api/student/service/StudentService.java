package com.example.api.student.service;

import com.example.api.student.model.Student;
import com.example.api.student.model.dto.StudentDto;
import com.example.api.student.model.dto.StudentFilter;
import com.example.api.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;

    @Value("${upload.path}")
    private String fileUpload;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll(StudentFilter filter) {
        return studentRepository.findAllByStudentName(filter.getName());
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(StudentDto studentDto) {
        Date birthday;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            birthday = formatter.parse(studentDto.getBirthday());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Student student = mapToStudent(studentDto);
        MultipartFile file = studentDto.getImage();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        student.setImageUrl(fileName);
        student.setBirthday(birthday);
        if (Objects.isNull(student.getId())) {
            student.setCreatedAt(new Date());
        } else {
            student.setUpdatedAt(new Date());
        }
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    private Student mapToStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setStudentName(studentDto.getStudentName());
        student.setAddress(studentDto.getAddress());
        student.setSex(studentDto.getSex());
        return student;
    }
}
