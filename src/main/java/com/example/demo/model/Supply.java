package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Supply {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String code;
	private Date supplyDate;
	
	@OneToMany(mappedBy="supply",cascade=CascadeType.ALL)
	//@JsonIgnoreProperties("supply")
	private List<Category> category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
}
