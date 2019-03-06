package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.util.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView(View.Public.class)
	private Long id;
	@JsonView(View.Public.class)
	private String name;
	@JsonView(View.Public.class)
	private String address;
	@JsonView(View.Public.class)
	private String contact;
	@JsonView(View.Public.class)
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id")
	//@JsonManagedReference
	//@JsonBackReference
	//@JsonIgnoreProperties("user")
	//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	//@JsonIdentityReference(alwaysAsId=true)
	@JsonView(View.Internal.class)
	private Department department;
	
	public User(String name, String address, String contact, String status) {
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.status = status;
	}
	public User() {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + ", status="
				+ status + "]";
	}
	
}
