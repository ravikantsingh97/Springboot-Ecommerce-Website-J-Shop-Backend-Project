package com.jshop.jshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringboot.dto.ProductOwner;

public interface ProductOwnerRepository extends JpaRepository<ProductOwner, Integer> {
	
	public ProductOwner findByProductOwnerEmail(String email);

}
