package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CagtegoryRepository extends JpaRepository<Category, Long>{

}
