package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Supply;

@Repository("genRepo")
public interface GenericRepository extends JpaRepository<Supply, Long>{

}
 