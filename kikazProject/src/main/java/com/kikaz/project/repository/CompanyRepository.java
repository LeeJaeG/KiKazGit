package com.kikaz.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikaz.project.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	 
}
