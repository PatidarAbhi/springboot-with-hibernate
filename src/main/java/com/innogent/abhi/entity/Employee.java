package com.innogent.abhi.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee implements Serializable {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false, name = "company_id")
	private Company company;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private long id;

	private String name;
	private LocalDate doj;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public Employee() {

	}

	public Employee(Company company, String name, LocalDate doj) {
		super();
		this.company = company;
		this.name = name;
		this.doj = doj;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", doj=" + doj + "]";
	}

}
