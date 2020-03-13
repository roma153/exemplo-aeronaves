package com.exemplo.aeronaves.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
@Entity
@Table(name = "aircraft")
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name field must not be empty")
	@Column(name = "name", nullable = false, length = 20)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Brand brand;
	
	@NotNull(message = "Year field must not be empty")
	@Column(name = "year", nullable = false)
	private Integer year;
	
	@Column(name = "description", length = 255)
	private String description;
	
	private boolean sold;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date created;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Date updated;
	
	/**
	 * Method updates {@link Aircraft} object through an {@link Aircraft parameter
	 * 
	 * @param aircraft is the modified object
	 */
	public void update(final Aircraft aircraft) {
		this.setName(aircraft.getName());
		this.setBrand(aircraft.getBrand());
		this.setYear(aircraft.getYear());
		this.setDescription(aircraft.getDescription());
		this.setSold(aircraft.isSold());
	}
	
	@PrePersist
	private void onCreation() {
		this.setCreated(new Date());
	}
	
	@PreUpdate
	private void onUpdate() {
		this.setUpdated(new Date());
	}
}
