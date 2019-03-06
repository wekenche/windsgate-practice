package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.util.View;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(View.Public.class)
	private Long id;
	@JsonView(View.Public.class)
	private String name;
	
	@OneToMany(mappedBy="department",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	//@JsonBackReference
	//@JsonManagedReference
	//@JsonIgnoreProperties("department")
	//@JsonIdentityReference(alwaysAsId=true)
	@JsonView(View.Internal.class)
	private List<User> user; 
	
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
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", user=" + user + "]";
	}
	
}
