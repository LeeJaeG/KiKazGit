package com.kikaz.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kikaz.project.model.Company;
import com.kikaz.project.model.Reservation;
import com.kikaz.project.model.Role;
import com.kikaz.project.model.Section;
import com.kikaz.project.model.User;
import com.kikaz.project.repository.CompanyRepository;

import com.kikaz.project.repository.ReservationRepository;

import com.kikaz.project.repository.SectionRepository;

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
