package com.jshop.jshopspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import com.jshop.jshopspringboot.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	/*
	 * save Product
	 */
	@PostMapping("/saveProduct")
	public ResponseStructure<Product> saveProductController(@RequestBody Product product) {
		
		return productService.saveProductService(product);
	}
		
}
