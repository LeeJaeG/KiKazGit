package com.kikaz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikaz.project.model.Company;
import com.kikaz.project.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	 
}
