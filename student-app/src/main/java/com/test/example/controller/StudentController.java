/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.controller;

import com.test.example.dto.Pagination;
import com.test.example.dto.ResponseData;
import com.test.example.entity.Student;
import com.test.example.service.StudentService;
import com.test.example.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kumaresan Sinniah
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Optional<Student> studentData = studentService.getStudentById(id);
        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/students")
    public ResponseEntity<ResponseData> getAllStudents() {
        try {
            ResponseData responseData = studentService.getAllStudents();
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/getStudentsByFilter")
    public ResponseEntity<ResponseData> getAuthorsByFilter(@RequestBody Student student,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "15", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC", required = false) String sortType) {
        try {
            ResponseData responseData = new ResponseData();

            Pageable paging = PageRequest.of(page, size);

            List<Student> _student = new ArrayList<>();

            if (sortType != null) {
                if (sortType.equalsIgnoreCase("ASC")) {
                    paging = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                } else {
                    paging = PageRequest.of(page, size, Sort.by(sortBy).descending());
                }
            }

            Page<Student> students = studentService.getStudentByFilter(student, paging);
            _student = students.getContent();

            if (_student.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                responseData.setData(new Pagination(_student, students));
                responseData.setMsgCode(Constants.SUCCESS_CODE);
                responseData.setMsgDesc(Constants.SUCCESS_STR);

                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/student")
    public ResponseEntity<ResponseData> saveStudent(@Valid @RequestBody Student student) {
        try {
            ResponseData responseData = studentService.saveStudent(student);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/student/{id}")
    public ResponseEntity<ResponseData> updateStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        try {
            ResponseData responseData = studentService.updateStudent(id, student);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/student/{id}")
    public ResponseEntity<ResponseData> deleteStudent(@PathVariable String id) {
        try {
            ResponseData responseData = studentService.deleteStudent(id);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/studentsByCourseId/{courseId}")
    public ResponseEntity<ResponseData> findByCoursesId(@PathVariable String courseId) {
        try {
            ResponseData responseData = studentService.findStudentsByCoursesId(courseId);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
