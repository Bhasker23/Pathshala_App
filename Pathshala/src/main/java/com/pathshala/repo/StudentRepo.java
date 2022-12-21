package com.pathshala.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathshala.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	 List<Student> findByName(String name);
}
