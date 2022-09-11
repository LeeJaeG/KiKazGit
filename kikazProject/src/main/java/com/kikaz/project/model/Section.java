package com.kikaz.project.model;


<<<<<<< HEAD
import java.time.LocalDateTime;
=======
import java.util.Date;
>>>>>>> origin/nh_branch

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class Section {

<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long section_id;
	private String section_name;
	private int price;
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime standardtime;
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime starttime;
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime endtime;
	private int maxnum_adult;
	private int maxnum_child;
	private String sect_imgpath;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Company company;
}
=======
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long section_id;
   private String section_name;
   private int price;
   
   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
   @Temporal(TemporalType.TIMESTAMP)
   private Date standard_time;
   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
   @Temporal(TemporalType.TIMESTAMP)
   private Date start_time;  
   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
   @Temporal(TemporalType.TIMESTAMP)
   private Date end_time;
   
   private int maxnum_adult;
   private int maxnum_child;
   private String sect_imgpath;
   @ManyToOne(fetch=FetchType.LAZY)
   @JsonIgnore
   private Company company;
}
>>>>>>> origin/nh_branch
