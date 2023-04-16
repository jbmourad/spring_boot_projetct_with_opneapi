package com.objis.masterclass2.service;

import com.objis.masterclass2.exeptions.StudentNotFoundExeption;
import com.objis.masterclass2.model.Student;
import com.objis.masterclass2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student saveStudent(Student student){
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudents(){

        return this.studentRepository.findAll();
    }

    public Optional<Student> getOneStudent(Long id){

        Optional<Student> student=this.studentRepository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundExeption(String.format("Student with %s not found"+id));
        }
        return this.studentRepository.findById(id);
    }

    public Student updateStudent(Student student, Long id){
        Optional<Student> studentExist=this.studentRepository.findById(id);
        if(!studentExist.isPresent()){
            throw new StudentNotFoundExeption(String.format("Student with %s not found"+id));
        }
        return this.studentRepository.save(student);
    }

    public void removeStudent(Long id){
        Optional<Student> student=this.studentRepository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundExeption(String.format("Student with %s not found"+id));
        }
        this.studentRepository.delete(student.get());
    }

}
