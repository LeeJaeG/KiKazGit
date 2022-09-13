package com.kikaz.project.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kikaz.project.model.Company;
import com.kikaz.project.model.Section;
import com.kikaz.project.repository.CompanyRepository;
import com.kikaz.project.repository.SectionRepository;
import com.kikaz.project.service.SectionService;


@Controller
public class SectionController {
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private CompanyRepository companyrepositiry;
	@Autowired
	private SectionService sectionService;
	
	
	@GetMapping("/sectioninsert")
	public String sectionjoin() {
		return "sectioninsert";
	}
	
	@PostMapping("/s_insert")
	public String s_insert(@RequestParam("file") MultipartFile file, Section sect,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime standard_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_time
			) {
		//Company com = companyrepositiry.findByCompanyname();
		String imageFileName = file.getOriginalFilename();
		String path = "C:\\Users\\17\\git\\KiKazGit\\kikazProject\\src\\main\\resources\\static\\sec_img\\";
		sect.setSect_imgpath(imageFileName);

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

		return "/main";

	}

	
	@RequestMapping(value = "/s_list",method = RequestMethod.GET)
	public String List(Model model,
		@PageableDefault(size = 5, sort = "sectionid", direction = Sort.Direction.ASC) Pageable pageable) {
		System.out.println("===============>"+pageable);
		sectionService.findBoardList(pageable);
		model.addAttribute("sectionList", sectionService.findBoardList(pageable));
		return "section/list";
	}
	
}
