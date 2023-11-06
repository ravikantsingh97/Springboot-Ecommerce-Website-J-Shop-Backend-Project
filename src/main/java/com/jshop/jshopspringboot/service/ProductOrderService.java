package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.ProductDao;
import com.jshop.jshopspringboot.dao.ProductOrderDao;
import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOrder;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
	public class ProductOrderService {

		@Autowired
		private ProductOrderDao orderDao;
		
		@Autowired
		private ResponseStructure<ProductOrder> responseStructure;
		
		@Autowired
		private HttpSession httpSession;
		
		@Autowired
		private ProductDao productDao;
		
		public ResponseStructure<ProductOrder> saveProductOrderService(int productId,int quantity,String address) {
			
			Product product=productDao.getProductByIdDao(productId);
			
			if((product!=null)&&(httpSession.getAttribute("password")!=null)) {
				
				if(product.getProductVerified().equalsIgnoreCase("yes")) {
					if(quantity<=product.getProudctQuantity()) {
						ProductOrder productOrder= orderDao.saveProductOrderDao(productId, quantity, address);
						responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
						responseStructure.setStatusMsg("Product order success");
						responseStructure.setDescription("Your order will be deliver after 3 days");
						responseStructure.setData(productOrder);
					}else {
						responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
						responseStructure.setStatusMsg("Product is not avaialable");
						responseStructure.setDescription("स्टॉक ख़त्म हो गया है ");
						responseStructure.setData(null);
					}
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setStatusMsg("Product is not verified");
					responseStructure.setDescription("Wait for the verification to purchase");
					responseStructure.setData(null);
				}
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("Please! Login first with user");
				responseStructure.setDescription("Kripya pahle login kariye");
				responseStructure.setData(null);
			}
			return responseStructure;
		}
	}
