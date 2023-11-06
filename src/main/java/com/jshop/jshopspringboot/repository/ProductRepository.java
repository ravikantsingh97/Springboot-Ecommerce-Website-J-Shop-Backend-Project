package com.jshop.jshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringboot.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
