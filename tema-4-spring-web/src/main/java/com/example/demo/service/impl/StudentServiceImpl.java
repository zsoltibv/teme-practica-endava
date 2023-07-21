package com.example.demo.service.impl;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> allStudents() {
        return this.studentRepository.getAllStudents();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        return this.studentRepository.addStudent(student);
    }

    @Override
    public Optional<Student> removeStudent(String lastName) {
        return this.studentRepository.removeStudent(lastName);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return this.studentRepository.updateStudent(student);
    }

    @Override
    public Optional<Student> patchStudent(String lastName, Integer age) {
        return this.studentRepository.patchStudent(lastName, age);
    }

    @Override
    public Optional<Student> getStudentByLastName(String lastName) {
        return this.studentRepository.getStudentByLastName(lastName);
    }

    @Override
    public Optional<Student> getStudentByAge(Integer age) {
        return this.studentRepository.getStudentByAge(age);
    }

    @Override
    public List<Student> changeFirstNameOfStudents() {
        return this.studentRepository.changeFirstNameOfStudents();
    }

    @Override
    public Optional<Student> deleteStudentWithFirstNameAndLastName(String firstName, String lastName) {
        return this.studentRepository.deleteStudentWithFirstNameAndLastName(firstName, lastName);
    }
}
