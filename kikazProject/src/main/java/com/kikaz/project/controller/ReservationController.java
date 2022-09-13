package com.kikaz.project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kikaz.project.model.Reservation;
import com.kikaz.project.model.Section;
import com.kikaz.project.model.User;
import com.kikaz.project.repository.ReservationRepository;
import com.kikaz.project.repository.SectionRepository;
import com.kikaz.project.repository.UserRepository;



@Controller
public class ReservationController {
   
   @Autowired
   private ReservationRepository reservationrepositiry;
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private SectionRepository sectionRepository;
   
   @GetMapping("/reservation")
   public String resevation() {
      
      return "reservation";
   }

   @PostMapping("/r_insert")
   @ResponseBody
   public ResponseEntity<Reservation> postEating(@RequestBody Reservation reservation, Principal principal,
		   Long id) {
      User user = userRepository.findByUsername(principal.getName());
      
      Section sec = sectionRepository.findbyid(id);
      System.out.println(user);
      reservation.setSection(sec);
      
      reservation.setUser(user);
      reservationrepositiry.save(reservation);
      return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
      }
   
   //아이디 기준으로 리스트 출력
   @RequestMapping("/r_listall")
   public String Reservation(Model m, Principal principal) {
      System.out.println("왔다");
      User user = userRepository.findByUsername(principal.getName());
      List<Reservation> reservation = reservationrepositiry.findAllByUser_Username(user.getUsername());
      System.out.println( reservation);
      m.addAttribute("reservation", reservation);
      return "/reservationlist";
   }
//   public ResponseEntity<Reservation> postEating(@RequestBody Reservation reservation) {
//      System.out.println("post request");
//      System.out.println(reservation.toString());
//      reservationrepositiry.save(reservation);
//      return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
//   }

   
   
   
   
   
}