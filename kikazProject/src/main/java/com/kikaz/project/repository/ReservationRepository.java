package com.kikaz.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikaz.project.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    public List<Reservation> findAllByUser_Username(String username);
    
}