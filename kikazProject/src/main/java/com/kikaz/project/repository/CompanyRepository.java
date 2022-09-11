package com.kikaz.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikaz.project.model.Company;
import com.kikaz.project.model.User;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	public List<Company> findByLocationContaining(String location);
}
