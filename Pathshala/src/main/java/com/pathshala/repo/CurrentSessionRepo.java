package com.pathshala.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathshala.models.CurrentSession;

@Repository
public interface CurrentSessionRepo extends JpaRepository<CurrentSession, String> {

	CurrentSession findByUserName(String userName);
}
