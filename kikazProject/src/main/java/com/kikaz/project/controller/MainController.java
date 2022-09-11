package com.kikaz.project.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kikaz.project.model.Company;
import com.kikaz.project.model.Role;
import com.kikaz.project.model.Section;
import com.kikaz.project.model.User;
import com.kikaz.project.repository.CompanyRepository;
import com.kikaz.project.repository.SectionRepository;
import com.kikaz.project.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CompanyRepository companyrepositiry;
	@Autowired
	private SectionRepository sectionRepository;

	
	
	
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
		// userRepository.save(user);
		user.setRole(Role.USER);
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
		return "redirect:/loginForm";

	}

	// 키즈카페 등록
	@GetMapping("/cafeinsert")
	public String cafejoin() {
		return "cafeinsert";
	}

	@PostMapping("/c_insert")
	public @ResponseBody String c_insert(MultipartFile file, Company com) {
		String imageFileName = file.getOriginalFilename();
		String path = "C:/Users/asd/git/KiKazGit/kikazProject/src/main/resources/static/company_images";
		
		com.setCom_imgpath(path);
		companyrepositiry.save(com);
		Path imaPath = Paths.get(path + imageFileName);
		try {
			Files.write(imaPath, file.getBytes());
		} catch (Exception e) {

		}
		return "cafesuccess";
	}

	// 파트 등록
	@GetMapping("/sectioninsert")
	public String sectionjoin() {
		return "sectioninsert";
	}
	
	
	@PostMapping("/s_insert")
	public String s_insert(@RequestParam("file") MultipartFile file, Section sect, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime standard_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end_time
			
			) {
		
		String imageFileName = file.getOriginalFilename();
		String path = "D:/sectionfile/";
		sect.setSect_imgpath(path+ imageFileName);
		
		Path sectPath = Paths.get(path + imageFileName);
		try {
			Files.write(sectPath, file.getBytes());
		} catch (Exception e) {

		}
		
		sect.setStandardtime(standard_time);
		sect.setStarttime(start_time);
		sect.setEndtime(end_time);
		sectionRepository.save(sect);
		System.out.println(sect);
		
		

		return "cafesuccess";

	}

}
