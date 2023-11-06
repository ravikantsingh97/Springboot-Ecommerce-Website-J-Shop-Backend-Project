package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.ProductDao;
import com.jshop.jshopspringboot.dao.ProductOwnerDao;
import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService{
	
	@Autowired
	private ResponseStructure<Product> responseStructure;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ProductOwnerDao ownerDao;
	
	
	
	/*
	 * save Product
	 */
	public ResponseStructure<Product> saveProductService(Product product) {
		
		if(httpSession.getAttribute("email")!=null) {
			
			
			ProductOwner productOwner=ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			
			if(productOwner!=null) {
				product.setProductOwner(productOwner);
			}
			product.setProductVerified("no");
			
			Product product1=productDao.saveProductDao(product);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("Product Addedd");
			responseStructure.setDescription("Now your product is added....your login session is valid for 200 seconds");
			responseStructure.setData(product1);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Your session is out ....please login");
			responseStructure.setDescription("only product owner can add product details if he/she is logged in");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
}

