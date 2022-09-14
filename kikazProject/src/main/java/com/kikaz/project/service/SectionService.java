package com.kikaz.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kikaz.project.model.Company;
import com.kikaz.project.model.Section;
import com.kikaz.project.repository.CompanyRepository;
import com.kikaz.project.repository.SectionRepository;


@Service
public class SectionService {
	
	private final SectionRepository sectionRepository;
	
	public SectionService(SectionRepository sectionRepository) {
		this.sectionRepository=sectionRepository;
	}
	
	public void save(Section section) {
		sectionRepository.save(section);
	}
	
	public Section findSectionByName(Long id) {
		return sectionRepository.findById(id).orElse(null);
	}
	public Section findBoardById(Long id) {
		return sectionRepository.findById(id).orElse(new Section());
	}

	public Page<Section> findBoardList(Pageable pageable){
		PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return sectionRepository.findAll(pageable);
	
	}
	
	public void deleteById(Long id) {
		sectionRepository.deleteById(id);
	}

}
