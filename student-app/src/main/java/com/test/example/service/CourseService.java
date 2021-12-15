/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.service;

import com.test.example.dto.ResponseData;
import com.test.example.entity.Course;
import com.test.example.entity.Student;
import com.test.example.repository.CourseRepository;
import com.test.example.repository.StudentRepository;
import com.test.example.utils.Constants;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kumaresan Sinniah
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }
    
    public ResponseData getAllStudents() {
        ResponseData responseData = new ResponseData();
        try{
            List<Student> courses = (List) studentRepository.findAll();
            responseData.setData(courses);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;            
        }
        catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }

    public ResponseData getAllCourses() {
        ResponseData responseData = new ResponseData();
        try{
            List<Course> courses = (List) courseRepository.findAll();
            responseData.setData(courses);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;            
        }
        catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }

    public ResponseData saveCourse(Course newCourse) {
        ResponseData responseData = new ResponseData();
        try {
            newCourse = courseRepository.saveAndFlush(newCourse);
            responseData.setData(newCourse);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;
        } catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }
    
    public ResponseData updateCourse(Course course) {
        ResponseData responseData = new ResponseData();
        if (!courseRepository.existsById(course.getId())) {
            responseData.setRemarks("DATA_NOT_FOUND");
            return responseData;
        }
        try {
            course = courseRepository.save(course);
            responseData.setData(course);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;
        } catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }
    
    public ResponseData deleteCourse(String id) {
        ResponseData responseData = new ResponseData();
        try {
            courseRepository.deleteById(id);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            responseData.setRemarks("Deleted Course with id " + id);
            return responseData;            
        } catch (Exception err) {
            responseData.setRemarks("Failed to delete Course with id " + id);
            return responseData;            
        }
    }
    
}
