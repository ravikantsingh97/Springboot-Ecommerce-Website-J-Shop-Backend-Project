package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.UserCartDao;
import com.jshop.jshopspringboot.dto.UserCart;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class UserCartService {

	@Autowired
	private UserCartDao cartDao;
	
	@Autowired
	private ResponseStructure<UserCart> responseStructure;
	

	@Autowired
	private HttpSession httpSession;
	/*
	 * add product in user cart
	 */
	public ResponseStructure<UserCart> addProductInUserCartService(int productQuantity,int productId) {
		
		if(httpSession.getAttribute("password")!=null) {
			
			UserCart userCart=cartDao.addProductInUserCartDao(productQuantity, productId);
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("Product Added in your cart successfully");
			responseStructure.setDescription("Now please order your added product");
			responseStructure.setData(userCart);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Your session is out ....please login with  user credential");
			responseStructure.setDescription("only user can add product in their user cart");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
}
