package com.kikaz.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikaz.project.model.Reservation;
import com.kikaz.project.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>{
	public List<Section> findByCompany_companyid(Long companyid);
}
