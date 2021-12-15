/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.specification;

import com.test.example.entity.Student;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Kumaresan Sinniah
 */
public class StudentSpecification {

    public static Specification<Student> getStudents(Student request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + request.getName().toLowerCase() + "%"));
            }

            query.orderBy(criteriaBuilder.desc(root.get("name")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
