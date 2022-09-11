package com.kikaz.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Company {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long companyid;
	private String company_name;
	private String business_name;
	private String location;
	private String com_imgpath;
	private String theme;
	private String content;
	private String com_tel;
	@OneToOne
	private User user;
}
