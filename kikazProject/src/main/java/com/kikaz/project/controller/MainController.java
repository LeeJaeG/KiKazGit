package com.kikaz.project.controller;


import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


import com.kikaz.project.model.User;

import com.kikaz.project.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/loginForm")
	public String login() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String join() {
		return "joinForm";
	}

	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user.toString());
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);

		userRepository.save(user);
		return "redirect:/loginForm";
	}

	@GetMapping("/qrex")
	public String prEx() {
		return "qrEX";
	}

	@GetMapping("/qr")
	public Object createQr(@RequestParam String url) throws WriterException, IOException {
		int width = 200;
		int height = 200;
		BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			MatrixToImageWriter.writeToStream(matrix, "PNG", out);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(out.toByteArray());
		}
	}

}
