package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository item;
	
	public List<Item> findAll(){
		return item.findAll();
	}
}
