package com.kikaz.project.model;

import java.time.LocalDate;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table
public class User {
	
	@Id
	private String username;
	private String u_name;
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	private String email;
	private String gender;
	private String user_PhoneNumber;
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	private String address;
}
