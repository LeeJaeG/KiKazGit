package com.kikaz.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Section section;
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
}
