package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Address {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String address;
}
