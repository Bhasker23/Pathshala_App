package com.pathshala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathshala.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
