package com.kikaz.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Long rsrv_id;
	private Integer adult_num;
	private Integer child_num;
	private Boolean success_status;
	private Date rStart;
	private Date rEnd;
	private Date rsrv_date;
	@ManyToMany(fetch=FetchType.LAZY, targetEntity = Section.class) //  클래스 명시
	@JsonIgnore 
	private List<Section> section;
	@ManyToOne(fetch=FetchType.LAZY ,targetEntity = User.class)
	@JsonIgnore
	private List<User> user;
}
