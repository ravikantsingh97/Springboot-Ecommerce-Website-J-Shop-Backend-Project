package com.jshop.jshopspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringboot.dto.ProductOrder;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import com.jshop.jshopspringboot.service.ProductOrderService;

@RestController
@RequestMapping("/order")
public class ProductOrderController {

	@Autowired
	private ProductOrderService orderService;
	
	@PutMapping("/productOrder/{productId}/{quantity}/{address}")
	public ResponseStructure<ProductOrder> saveProductOrderController(@PathVariable int productId,@PathVariable int quantity,@PathVariable String address) {
		
		return orderService.saveProductOrderService(productId, quantity, address);
	}
}
