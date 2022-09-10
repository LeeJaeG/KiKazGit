package com.kikaz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kikaz.project.model.User;


public interface UserRepository extends JpaRepository<User, Long>  {

	public User findByUsername(String username);
	
	
}
