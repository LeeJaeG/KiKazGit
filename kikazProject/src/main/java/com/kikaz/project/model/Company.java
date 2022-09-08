package com.kikaz.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Company {
	@Id
	private Long company_id;
	private String location;
	private String com_imgpath;
	private String theme;
	private String content;
	private String com_tel;
}
