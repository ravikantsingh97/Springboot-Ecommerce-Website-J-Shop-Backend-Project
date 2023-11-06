package com.jshop.jshopspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringboot.dto.Admin;
import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import com.jshop.jshopspringboot.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/*
	 * 
	 * signup code for admin saveMethod
	 * 
	 */
	@PostMapping("/saveAdmin")
	public ResponseStructure<Admin> saveAdminController(@RequestBody Admin admin) {
		return adminService.saveAdminService(admin);
	}
	
	/*
	 * login with admin
	 */
	@GetMapping("/loginAdmin/{email}/{password}")
	public ResponseStructure<Admin> loginWithAdminService(@PathVariable String email,@PathVariable String password) {
		return adminService.loginWithAdminService(email, password);
	}
	
	/*
	 * getAllProductOwnerAdminDao
	 */
	@GetMapping("/getAllProductOwner")
	public ResponseStructure<List<ProductOwner>> getAllProductOwnerAdminDao() {
		return adminService.getAllProductOwnerAdminService();
	}
	
	/*
	 * getProductOwnerById
	 */
	@GetMapping("/getProductOwnerById/{productOwnerId}")
	public ResponseStructure<ProductOwner> getProductOwnerByIdAdminDao(@PathVariable int productOwnerId) {
		return adminService.getProductOwnerByIdAdminService(productOwnerId);
	}
	
	/*
	 * verify product owner from no to yes and unverified from yes to no
	 */
	@PutMapping("/verifyProductOwner/{productOwnerId}")
	public ResponseStructure<ProductOwner> verifyProductOwner(@PathVariable int productOwnerId) {
		
		return adminService.verifyProductOwnerService(productOwnerId);
	}
	
	/*
	 * verify Product details By Admin
	 */
	@PutMapping("/verifyProductByAdmin/{productId}")
	public ResponseStructure<Product> verifyProductDetailsByAdminController(@PathVariable int productId) {
		return adminService.verifyProductDetailsByAdmin(productId);
	}
}
