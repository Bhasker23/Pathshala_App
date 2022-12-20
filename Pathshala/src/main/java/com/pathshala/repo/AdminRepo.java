package com.pathshala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathshala.models.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {

	
}
