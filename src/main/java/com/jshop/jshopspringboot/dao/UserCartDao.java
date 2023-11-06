package com.jshop.jshopspringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.UserCart;
import com.jshop.jshopspringboot.repository.UserCartRepository;

@Repository
public class UserCartDao {

	@Autowired
	private UserCartRepository cartRepository;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserCart  userCart;
	
	@Autowired
	private UserDao userDao;
	
	/*
	 * add product in user cart
	 */
	public UserCart addProductInUserCartDao(int productQuantity,int productId) {
		
		Product product=productDao.getProductByIdDao(productId);
		
		if(product!=null) {
			userCart.setProduct(product);
			userCart.setProductQuantity(productQuantity);
			//System.out.println(userDao.getUserId());
			userCart.setUser(userDao.getUser());
			
			return cartRepository.save(userCart);
		}else {
			return null;
		}
	}
}
