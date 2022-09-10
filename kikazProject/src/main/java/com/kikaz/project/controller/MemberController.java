package com.kikaz.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kikaz.project.model.User;
import com.kikaz.project.repository.UserRepository;



@Controller
public class MemberController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping({"/",""})//주소창에 아무것도 안치거나 /만 쳐도 나옴
	public String home() {
		return "/index";
	}

	//로그인 페이지
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/loginForm";
	}
	
	//회원가입 폼페이지
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "/joinForm";
	}
	
	//회원가입 처리페이지
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(User user) {
		//System.out.println(user.toString());//제대로 콘솔에 나오는지 확인하려고함
		//user.setRole("ROLE_CEO");
		//roll값을 ROLE_USER로 넣음	
		String rawPassword=user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		System.out.println(rawPassword.toString());//암호화됐는지 확인해보기
		user.setPassword(encPassword);
		userRepository.save(user);

		return "redirect:/loginForm";//redirect:이동하라는 의미 로그인폼으로 이동해라
	}
}
