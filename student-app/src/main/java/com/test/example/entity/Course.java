/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.entity;

import com.test.example.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "courses")
public class Course implements BaseEntity {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    
    @Column(name = "name")
    @NotEmpty(message = "Please provide a course name")
    private String name;
    
    @Column(name = "fees")
    private BigDecimal fees;
    
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

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private List<Student> students;

    public Course() {

    }

    @Override
    public String toString() {
        return "Course{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", course_fees='" + fees + '\''                
                + ", students=" + students
                + '}';
    }

    public String toJsonString() {
        return "{"
                + "course_id=" + id
                + ", course_name='" + name + '\''
                + ", course_fees='" + fees + '\''
                + ", students=" + students
                + '}';
    }
}
