package com.example.demo.repository;

import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> getAllStudents();

    Optional<Student> addStudent(Student student);

    Optional<Student> removeStudent(String lastName);

    Optional<Student> updateStudent(Student student);

    Optional<Student> patchStudent(String lastName, Integer age);

    Optional<Student> getStudentByLastName(String lastName);

    Optional<Student> getStudentByAge(Integer age);

    Optional<List<Student>> changeFirstNameOfStudents();

    Optional<Student> deleteStudentWithFirstNameAndLastName(String firstName, String lastName);
}
