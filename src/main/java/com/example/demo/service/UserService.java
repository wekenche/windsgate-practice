package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}

	public void save(List<User> listUser) {
		userRepo.save(listUser);
	}
	
	//for department
	public Page<Department> getAllDepartment(Pageable pageable) {
		return deptRepo.findAll(pageable);
	}

	public Department saveDepartment(Department department) {
		
		return deptRepo.save(department);
	}
}
