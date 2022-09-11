package com.kikaz.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table
public class Company {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long company_id;
	private String company_name;
	private String business_name;
	private String location;
	private String com_imgpath;
	private String theme;
	private String content;
	private String com_tel;
	
	@Builder
	public Company(String company_name, String business_name,
			String location,String com_imgpath,String com_tel,String theme,
			String content) {
		this.company_name = company_name;
		this.business_name = business_name;
		this.location = location;
		this.com_imgpath = com_imgpath;
		this.com_tel = com_tel;
		this.theme = theme;
		this.content = content;
	}
}
