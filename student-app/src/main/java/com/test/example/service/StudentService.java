/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.service;

import com.test.example.dto.ResponseData;
import com.test.example.entity.Student;
import com.test.example.repository.StudentRepository;
import com.test.example.specification.StudentSpecification;
import com.test.example.utils.Constants;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kumaresan Sinniah
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }
    
    public ResponseData getAllStudents() {
        ResponseData responseData = new ResponseData();
        try{
            List<Student> students = (List) studentRepository.findAll();
            responseData.setData(students);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;            
        }
        catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }
    
    public Page<Student> getStudentByFilter(Student student, Pageable page) {
        return studentRepository.findAll(StudentSpecification.getStudents(student), page);
    }
    
    public ResponseData saveStudent(Student newStudent) {
        ResponseData responseData = new ResponseData();
        try {
            newStudent = studentRepository.saveAndFlush(newStudent);
            responseData.setData(newStudent);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;
        } catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }
    
    public ResponseData updateStudent(String id ,Student student) {
        ResponseData responseData = new ResponseData();
        try {
            Student studentFromDb = studentRepository.findById(id).get();
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            studentFromDb.setContactNo(student.getContactNo());
            student = studentRepository.save(studentFromDb);
            responseData.setData(student);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;
        } catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }
    
    public ResponseData deleteStudent(String id) {
        ResponseData responseData = new ResponseData();
        try {
            studentRepository.deleteById(id);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            responseData.setRemarks("Deleted Student with id " + id);
            return responseData;            
        } catch (Exception err) {
            responseData.setRemarks("Failed to delete Student with id " + id);
            return responseData;            
        }
    }
    
    public ResponseData findStudentsByCoursesId(String courseId) {
        ResponseData responseData = new ResponseData();
        try{
            List<Object> students = (List) studentRepository.findByCourses_Id(courseId);
            responseData.setData(students);
            responseData.setMsgCode(Constants.SUCCESS_CODE);
            responseData.setMsgDesc(Constants.SUCCESS_STR);
            return responseData;            
        }
        catch (DataAccessException ex) {
            responseData.setRemarks(ex.getLocalizedMessage());
            return responseData;
        }
    }    
}
