package com.kikaz.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kikaz.project.model.Reservation;
import com.kikaz.project.repository.ReservationRepository;

@Controller
public class ReservationController {
	@Autowired
	private ReservationRepository reservationrepositiry;
	
	@GetMapping("/reservation")
	public String resevation() {
		return "reservation";
	}

	@PostMapping("/r_insert")
	@ResponseBody
	public ResponseEntity<Reservation> postEating(@RequestBody Reservation sleepData) {
		System.out.println("post request");
		System.out.println(sleepData.toString());
		reservationrepositiry.save(sleepData);
		return new ResponseEntity<Reservation>(sleepData, HttpStatus.CREATED);

	}
}
