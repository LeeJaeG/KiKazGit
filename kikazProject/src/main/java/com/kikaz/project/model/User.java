package com.kikaz.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User {
	@Id
	private Long user_id;
	private String username;
	private String password;
	private Date birthDate;
	private String email;
	private String gender;
	private String user_PhoneNumber;
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	private String address;
}
