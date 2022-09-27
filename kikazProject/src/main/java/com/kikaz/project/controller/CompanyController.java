package com.kikaz.project.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.kikaz.project.model.Company;
import com.kikaz.project.model.User;
import com.kikaz.project.repository.CompanyRepository;
import com.kikaz.project.repository.UserRepository;
import com.kikaz.project.service.CompanyService;


@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyRepository companyrepositiry;
	@Autowired
	private UserRepository userRepositiry;
	
	@GetMapping("/cafeinsert")
	public String cafejoin() {
		//System.out.println( companyrepositiry.findByLocationContaining("대구"));
		return "cafeinsert";
	}
	
	@PostMapping("/c_insert")
	public String c_insert(Principal principal,MultipartFile file, Company com) {
		User user = userRepositiry.findByUsername(principal.getName());
		com.setUser(user);
		String imageFileName = file.getOriginalFilename();
		String path = "C:\\Users\\17\\git\\KiKazGit\\kikazProject\\src\\main\\resources\\static\\image\\";
		com.setCom_imgpath(imageFileName);
		companyrepositiry.save(com);
		Path imaPath = Paths.get(path + imageFileName);
		try {
			Files.write(imaPath, file.getBytes());
		} catch (Exception e) {
		}

		return "main";

	}

	

	@RequestMapping(value = "/c_list",method = RequestMethod.GET)
	public String List(Model model,
		@PageableDefault(size = 5, sort = "companyid", direction = Sort.Direction.ASC) Pageable pageable) {
		System.out.println("===============>"+pageable);
		companyService.findBoardList(pageable);
		model.addAttribute("companyList", companyService.findBoardList(pageable));

		return "companylist";
	}

	
	@GetMapping("/c_user_list")
	public String getuserList(Model model,
			@PageableDefault(size = 5, sort = "companyid", direction = Sort.Direction.DESC) Pageable pageable) {
		companyService.findBoardList(pageable);
		model.addAttribute("companyList", companyService.findBoardList(pageable));

		return "companyuserlist";
	}
	

}

