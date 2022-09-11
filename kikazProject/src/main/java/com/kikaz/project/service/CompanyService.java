package com.kikaz.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kikaz.project.model.Company;
import com.kikaz.project.repository.CompanyRepository;


@Service
public class CompanyService {
	
	private final CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository=companyRepository;
	}
	
	public void save(Company company) {
		companyRepository.save(company);
	}
	
	public Company findCompanyByName(Long id) {
		return companyRepository.findById(id).orElse(new Company());
	}
	public Company findBoardById(Long id) {
		return companyRepository.findById(id).orElse(new Company());
	}

	public Page<Company> findBoardList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return companyRepository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		companyRepository.deleteById(id);
	}

}
