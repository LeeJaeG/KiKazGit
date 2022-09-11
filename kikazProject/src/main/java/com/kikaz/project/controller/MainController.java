package com.kikaz.project.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.multipart.MultipartFile;

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

	@GetMapping("/cafeinsert")
	public String cafejoin() {
		return "cafeinsert";
	}

	@PostMapping("/c_insert")
	public @ResponseBody String c_insert(MultipartFile file, Company com) {
		String imageFileName = file.getOriginalFilename();
		System.out.println(imageFileName);
		String path = "C:\\Users\\17\\git\\KiKazGit\\kikazProject\\src\\main\\resources\\static\\image\\";
		com.setCom_imgpath(path+imageFileName);
		companyrepositiry.save(com);
		Path imaPath = Paths.get(path + imageFileName);
		try {
			Files.write(imaPath, file.getBytes());
		} catch (Exception e) {

		}
		return "cafesuccess";
	}
	
	@GetMapping("/sectioninsert")
	   public String sectionjoin() {
	      return "sectioninsert";
	   }
	   
	   @PostMapping("/s_insert")
	   public @ResponseBody String s_insert(MultipartFile file, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Section sect ) {
	      System.out.println(sect);
	      String imageFileName = file.getOriginalFilename();
	      String path = "D:/sectionfile/";
	      sect.setSect_imgpath(path);
	      sectionRepository.save(sect);
	      Path sectPath = Paths.get(path + imageFileName);
	      try {
	         Files.write(sectPath, file.getBytes());
	      } catch (Exception e) {

	      }

	      return "cafesuccess";

	   }
	   
		/*
		 * @RequestMapping("/cafeimg") public String cafe_img(Model
		 * mod,@RequestParam(value = "id", defaultValue = "0")Long id) {
		 * Optional<Company> com = companyrepositiry.findById(id);
		 * mod.addAttribute("com", com); System.out.println("====>"+com); return
		 * "cafeimage"; }
		 */
	   @RequestMapping("/cafeimg")   
	   public String SelectDummies(Model mod) {
		   Long id = (long) 18;
		   Optional<Company> result = companyrepositiry.findById(id);
		   System.out.println("=============================");
		   Company com = result.get();
		   System.out.println(com);
		   
		   mod.addAttribute("com", com);
		   return "cafeimage";
	   }
	  
	   
}
