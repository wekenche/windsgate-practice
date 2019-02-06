package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, Long> {
	
	//public List<User> findAll();
}
