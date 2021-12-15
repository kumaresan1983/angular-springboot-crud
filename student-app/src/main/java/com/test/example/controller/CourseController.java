/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.controller;

import com.test.example.dto.ResponseData;
import com.test.example.entity.Course;
import com.test.example.service.CourseService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kumaresan Sinniah
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String id) {
        Optional<Course> courseData = courseService.getCourseById(id);
        if (courseData.isPresent()) {
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("/courses")
    public ResponseEntity<ResponseData> getAllCourses() {
        try {
            ResponseData responseData = courseService.getAllCourses();
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/course")
    public ResponseEntity<ResponseData> saveCourse(@Valid @RequestBody Course course) {
        try {
            ResponseData responseData = courseService.saveCourse(course);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    @PutMapping("/course")
    public ResponseEntity<ResponseData> updateCourse(@Valid  @RequestBody Course course) {
        try {
            ResponseData responseData = courseService.updateCourse(course);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/course/{id}")
    public ResponseEntity<ResponseData> deleteCourse(@PathVariable String id) {
        try {
            ResponseData responseData = courseService.deleteCourse(id);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
