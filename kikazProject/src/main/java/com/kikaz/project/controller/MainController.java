package com.kikaz.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/main")
	public String home() {
		return "123";
	}
	
	@RequestMapping("/cafeinsert")
	public String index() {
		return "cafeinsert";
	}

}
