package com.pathshala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathshala.models.StudentAddresses;

@Repository
public interface StudentAddressRepo extends JpaRepository<StudentAddresses, Integer> {

}
