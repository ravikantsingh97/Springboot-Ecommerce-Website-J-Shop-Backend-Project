package com.jshop.jshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringboot.dto.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{
	
	
}
