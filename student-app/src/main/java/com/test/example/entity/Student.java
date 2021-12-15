/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.entity;

import com.test.example.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Kumaresan Sinniah
 */
@Entity
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@Table(name = "students")
public class Student implements BaseEntity {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide a student name")    
    private String name;

    @Column(name = "age")
    @NotEmpty(message = "Please provide student age")    
    private String age;

    @Column(name = "contact_no")
    @NotEmpty(message = "Please provide student contact no")    
    private String contactNo;

    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String updatedBy;


    @ManyToMany(cascade = {CascadeType.REFRESH}, targetEntity = Course.class)
    @JoinTable(name = "student_courses",
            joinColumns = {
                @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "course_id")
            })
    @JsonIgnoreProperties("students")
    private List<Course> courses;

    public Student() {

    }


    @Override
    public String toString() {
        return "Student{"
                + "student_id='" + id + '\''
                + ", student_name='" + name + '\''
                + ", student_age=" + age 
                + ", student_contact_no='" + contactNo + '\''
                + ", createdAt=" + createdDatetime 
                + ", createdBy='" + createdBy + '\''
                + ", lastUpdatedAt=" + updatedDatetime 
                + ", lastUpdatedBy='" + updatedBy + '\''
                + ", courses=" + courses
                + '}';
    }

    public String toJsonString() {
        return "{"
                + "student_id='" + id + '\''
                + ", student_name='" + name + '\''
                + ", student_age=" + age 
                + ", student_contact_no='" + contactNo + '\''
                + ", createdAt=" + createdDatetime 
                + ", createdBy='" + createdBy + '\''
                + ", lastUpdatedAt=" + updatedDatetime 
                + ", lastUpdatedBy='" + updatedBy + '\''
                + ", courses=" + courses
                + '}';
    }
}
