package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.ProductOwnerDao;
import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

/**
 * @author Ravi Kant Singh
 */

@Service
public class ProductOwnerService {

	/*
	 * to call save method of productowner dao class
	 */
    
	@Autowired
	private ProductOwnerDao ownerDao;
	
	/*
	 * to call validatePassword() and validateEmail() method from AdminService class
	 */
	@Autowired
	private AdminService adminService;
	
	
	/*
	 * to display some message on postman api
	 */
	@Autowired
	private ResponseStructure<ProductOwner> responseStructure;
	
	
	@Autowired
	private HttpSession httpSession;
	
	/*
	 * productOwner Register
	 */
	public ResponseStructure<ProductOwner> saveProductOwnerService(ProductOwner productOwner) {
		
		String email = adminService.validateEmail(productOwner.getProductOwnerEmail());
		String password = adminService.validatePassword(productOwner.getProductOwnerPassword());
		
		if(email!=null) {
			if(password!=null) {
				ProductOwner owner=ownerDao.saveProductOwnerDao(productOwner);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("product-owner----Registered");
				responseStructure.setDescription("congratulation---Please--Login");
				responseStructure.setData(owner);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("Check--Your---Password");
				responseStructure.setDescription("your password length should be 8 character along with 1 lower case,1 upperCase,atleast 1 special Character(!@#$*&%),1 number");
				responseStructure.setData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Check--Your---Email");
			responseStructure.setDescription("your email should contain atleast alphabetnumber@hgja.com");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
	
	/*
	 * 
	 * loginWithProductOwner
	 */
	public ResponseStructure<ProductOwner> loginWithProductOwner(String email,String password) {
		
		ProductOwner productOwner=ownerDao.getProductOwnerByEmail(email);
		
		if(productOwner!=null) {
			
			if(productOwner.getAdminVerify().equalsIgnoreCase("yes")) {
				if(productOwner.getProductOwnerPassword().equals(password)) {
					httpSession.setAttribute("email", productOwner.getProductOwnerEmail());
					httpSession.setMaxInactiveInterval(200);
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setStatusMsg("login success fully.....");
					responseStructure.setDescription("Now you can add your product details");
					responseStructure.setData(productOwner);
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setStatusMsg("password is incorrect please check it");
					responseStructure.setDescription("please check your password once again ...or press forgot your password button...");
					responseStructure.setData(null);
				}
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("You are Not verified productOwner");
				responseStructure.setDescription("Please contact with admin and verify yourself....454586869");
				responseStructure.setData(null);
			}
			
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Check--Your---Email");
			responseStructure.setDescription("your email is incorrect");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
}
