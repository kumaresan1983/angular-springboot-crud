/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.repository;

//import com.test.example.repository.AuthorRepository;
//import com.test.example.entity.Author;
//import com.test.example.entity.Book;
import com.test.example.entity.Student;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *
 * @author Kumaresan Sinniah
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void initUseCase() {
        Student student = new Student();

        student = new Student();
        student.setId("1");
        student.setName("kumaresan");
        student.setAge("20");
        student.setContactNo("88448844");

        List<Student> students = new ArrayList<>();
        students.add(student);
        studentRepository.saveAll(students);
    }

    @AfterEach
    public void destroyAll() {
        studentRepository.deleteAll();
    }

    @Test
    void findAll_success() {
        List<Student> allStudents = studentRepository.findAll();
        assertThat(allStudents.size()).isGreaterThanOrEqualTo(1);
    }
}
