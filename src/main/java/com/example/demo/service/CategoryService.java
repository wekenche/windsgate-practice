package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CagtegoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CagtegoryRepository cat;
	
	public List<Category> findAll(){
		return cat.findAll();
	}
}
