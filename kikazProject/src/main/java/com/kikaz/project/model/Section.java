package com.kikaz.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class Section {

	@Id
	private Long section_id;
	private String section_name;
	private int price;
	private Date standard_time;
	private Date start_time;
	private Date end_time;
	private int maxnum_adult;
	private int maxnum_child;
	private String sect_imgpath;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Company company;
}
