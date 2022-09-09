package com.kikaz.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kikaz.project.model.Role;
import com.kikaz.project.model.User;
import com.kikaz.project.repository.UserRepository;





@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/loginForm")
	public String home() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String main() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user.toString());
		//userRepository.save(user);
		user.setRole(Role.USER);
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
		return "redirect:/loginForm";
	}

}
