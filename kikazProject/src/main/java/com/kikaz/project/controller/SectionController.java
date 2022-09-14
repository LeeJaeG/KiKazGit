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
	private String companyid;
	
	@GetMapping("/sectioninsert")
	public String sectionjoin(Model model,@RequestParam("companyid") String companyid) {
		model.addAttribute("companyid",companyid);
		return "sectioninsert";
	}

	@PostMapping("/s_insert")
	public String s_insert(@RequestParam("file") MultipartFile file,@RequestParam(value="companyid") String companyid,
			Section sect,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime standard_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_time,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_time) {
		System.out.println(companyid);
		String imageFileName = file.getOriginalFilename();
		String path = "C:\\Users\\15\\git\\KiKazGit\\kikazProject\\src\\main\\resources\\static\\sec_img\\";
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

		return "cafesuccess";

	}
	
	@GetMapping("/s_list")
	public String getList(Model model,
			@PageableDefault(size = 5, sort = "sectionid", direction = Sort.Direction.DESC) Pageable pageable) {
		sectionService.findBoardList(pageable);
		model.addAttribute("companyList", sectionService.findBoardList(pageable));

		return "sectionlist";
	}
	

	@RequestMapping("/secimg")
	public String Secimg(Model mod) {
		Long id = (long) 1;
		Optional<Company> result = companyrepositiry.findById(id);
		System.out.println("=============================");
		Company com = result.get();
		System.out.println(com);

		mod.addAttribute("com", com);
		return "secimage";
	}
}
