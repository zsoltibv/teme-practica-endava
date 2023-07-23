package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //get all students
    @GetMapping("/all")
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> student = this.studentService.allStudents();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //add a new student in the list
    @PostMapping()
    public ResponseEntity<Optional<Student>> addStudent(@RequestBody Student student) {
        Optional<Student> studentAdded = this.studentService.addStudent(student);
        return new ResponseEntity<>(studentAdded, HttpStatus.CREATED);
    }

    //delete a student from the list by lastName ='X'
    @DeleteMapping("/{lastName}")
    public ResponseEntity<Object> removeStudent(@PathVariable String lastName) {
        Optional<Student> removedStudent = this.studentService.removeStudent(lastName);
        if (removedStudent.isPresent()) {
            return new ResponseEntity<>(removedStudent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Student with lastName " + lastName + " was not found in our list", HttpStatus.NOT_FOUND);
    }
    //update a student with lastName = 'x'

    @PutMapping()
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        Optional<Student> updatedStudent = this.studentService.updateStudent(student);
        if (updatedStudent.isPresent()) {
            return new ResponseEntity<>(updatedStudent.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with lastName " + student.getLastName() + " was not found in our list", HttpStatus.NOT_FOUND);
    }

    //patch the age of a student with lastName = 'x' to be 39
    @PatchMapping
    public ResponseEntity<Object> patchStudent(@RequestParam String lastName, @RequestParam Integer age) {
        Optional<Student> studentPatched = this.studentService.patchStudent(lastName, age);
        if (studentPatched.isPresent()) {
            return new ResponseEntity<>(studentPatched.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with lastName " + lastName + " was not found in our list", HttpStatus.NOT_FOUND);
    }

    //get student by lastName -> "Bularca" using path variable
    @GetMapping("/{lastName}")
    public ResponseEntity<Object> getStudentByLastName(@PathVariable String lastName){
        Optional<Student> studentFound = this.studentService.getStudentByLastName(lastName);

        if (studentFound.isPresent()) {
            return new ResponseEntity<>(studentFound, HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with lastName " + lastName + " was not found in our list", HttpStatus.NOT_FOUND);
    }

    //get student by age -> 18 using request param
    @GetMapping
    public ResponseEntity<Object> getStudentByAge(@RequestParam Integer age){
        Optional<Student> studentFound = this.studentService.getStudentByAge(age);

        if (studentFound.isPresent()) {
            return new ResponseEntity<>(studentFound, HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with age " + age + " was not found in our list", HttpStatus.NOT_FOUND);
    }

    //change the firstName of all students that are having the age higher than 30 to "Happy Coding"
    @PatchMapping("/change")
    public ResponseEntity<Object> changeFirstNameOfStudents(){
        Optional<Optional<List<Student>>> students = Optional.ofNullable(this.studentService.changeFirstNameOfStudents());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //delete student who has firstName -> Stefan and
    // lastName -> Iordache using request parameters
    @DeleteMapping
    public ResponseEntity<Object> deleteStudentWithFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        Optional<Student> removedStudent = this.studentService.deleteStudentWithFirstNameAndLastName(firstName, lastName);
        if (removedStudent.isPresent()) {
            return new ResponseEntity<>(removedStudent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Student with first name " + firstName + "and last name" + lastName + " was not found in our list", HttpStatus.NOT_FOUND);
    }
}
