/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.controller;

import com.test.example.dto.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.example.entity.Student;
import com.test.example.service.StudentService;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;
    private Student student;
    private ResponseData responseData;
    private List<Student> studentList;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        student = new Student();
        student.setId("1");
        student.setName("kumaresan");
        student.setAge("20");
        student.setContactNo("88448844");

        studentList = new ArrayList<Student>();
        studentList.add(student);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @AfterEach
    void tearDown() {
        student = null;
        studentList = null;
    }

    @Test
    public void saveStudent() throws Exception {
        mockMvc.perform(post("/student").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(student))).
                andExpect(status().isCreated());
        verify(studentService, times(1)).saveStudent(any());
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(student))).
                andDo(MockMvcResultHandlers.print());
        verify(studentService).getAllStudents();
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void getAuthorById() throws Exception {
        when(studentService.getStudentById(student.getId())).thenReturn(Optional.of(student));
        mockMvc.perform(get("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(student)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
