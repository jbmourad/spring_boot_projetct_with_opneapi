package com.objis.masterclass2.controller;

import com.objis.masterclass2.model.Student;
import com.objis.masterclass2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/rest", name="app_students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping(path = "/students", name="create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student add (@RequestBody Student student){
        return this.studentService.saveStudent(student);
    }

    @GetMapping(path = "/students", name="list")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> list(){
        return this.studentService.getAllStudents();
    }

    @GetMapping(path = "/students/{id}",name="read")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Student> read(@PathVariable Long id){
        return this.studentService.getOneStudent(id);
    }

    @PutMapping(path = "/students/{id}", name="update")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@RequestBody Student student, @PathVariable Long id){
        return this.studentService.updateStudent(student,id);
    }

    @DeleteMapping(path = "/students/{id}",name="remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        this.studentService.removeStudent(id);
    }
}
