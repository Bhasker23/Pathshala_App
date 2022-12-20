package com.pathshala.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathshala.models.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
