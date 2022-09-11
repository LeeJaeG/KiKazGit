package com.kikaz.project.model;


import java.time.LocalDateTime;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rsrv_id;
	private Integer adult_num;
	private Integer child_num;
	private Boolean success_status;
	private LocalDateTime rstart;
	private LocalDateTime rend;
	@ManyToOne(fetch=FetchType.LAZY, targetEntity = Section.class) //  클래스 명시
	@JsonIgnore 
	private Section section;
	@ManyToOne(fetch=FetchType.LAZY ,targetEntity = User.class)
	@JsonIgnore
	private User user;
}
