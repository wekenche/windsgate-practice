package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supply;
import com.example.demo.repository.GenericRepository;

@Service("supplyService")
public class SupplyService {

	@Autowired
	GenericRepository genRepo;
	
	
	public List<Supply> findAll(){
		return genRepo.findAll();
	}
}
