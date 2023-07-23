package com.example.demo.repository.impl;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public StudentRepositoryImpl() {
        this.students.add(new Student("Stefan", "Iordache", 25));
        this.students.add(new Student("Andrei", "Bularca", 18));
        this.students.add(new Student("Emilian", "Moise", 38));
        this.students.add(new Student("Andreea", "Voda", 44));
        this.students.add(new Student("Raluca", "Mihalcea", 22));
    }

    public List<Student> getAllStudents() {
        return this.students;
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        Student studentAdded = new Student(student.getFirstName(), student.getLastName(), student.getAge());
        this.students.add(studentAdded);
        return Optional.of(studentAdded);
    }

    @Override
    public Optional<Student> removeStudent(String lastName) {
        Optional<Student> studentFound = this.students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
        studentFound.ifPresent(this.students::remove);

        return studentFound;
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        Optional<Student> optionalStudentUpdated = this.students.stream()
                .filter(studentUp -> studentUp.getLastName().equalsIgnoreCase(student.getLastName()))
                .findFirst();
        Student studentUpdated = null;
        if (optionalStudentUpdated.isPresent()) {
            studentUpdated = optionalStudentUpdated.get();
            this.students.remove(studentUpdated);
            studentUpdated.setFirstName(student.getFirstName());
            studentUpdated.setAge(student.getAge());
            this.students.add(studentUpdated);
        }
        return Optional.ofNullable(studentUpdated);
    }

    @Override
    public Optional<Student> patchStudent(String lastName, Integer age) {
        return this.students.stream()
                .filter(studentUp -> studentUp.getLastName().equalsIgnoreCase(lastName))
                .map(student -> {
                    student.setAge(age);
                    return student;
                })
                .findFirst();
    }

    @Override
    public Optional<Student> getStudentByLastName(String lastName) {
        Optional<Student> studentFound = this.students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .findFirst();

        return studentFound;
    }

    @Override
    public Optional<Student> getStudentByAge(Integer age) {
        Optional<Student> studentFound = this.students.stream()
                .filter(student -> Objects.equals(student.getAge(), age))
                .findFirst();

        return studentFound;
    }

    @Override
    public Optional<List<Student>> changeFirstNameOfStudents() {
        List<Student> students = this.students.stream()
                .filter(student -> student.getAge() > 30)
                .map(student -> {
                    student.setFirstName("Happy Coding");
                    return student;
                })
                .toList();

        return Optional.of(students);
    }

    @Override
    public Optional<Student> deleteStudentWithFirstNameAndLastName(String firstName, String lastName) {
        Optional<Student> studentFound = this.students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .findFirst();

        studentFound.ifPresent(this.students::remove);

        return studentFound;
    }
}
