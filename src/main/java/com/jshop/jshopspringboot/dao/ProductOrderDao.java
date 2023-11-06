package com.jshop.jshopspringboot.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOrder;
import com.jshop.jshopspringboot.repository.ProductOrderRepository;
import com.jshop.jshopspringboot.repository.ProductRepository;

@Repository
public class ProductOrderDao {

	@Autowired
	private ProductOrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Product product;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductOrder productOrder;
	
	public ProductOrder saveProductOrderDao(int productId,int quantity,String address) {
		Product product = productDao.getProductByIdDao(productId);
//		System.out.println("By user = "+quantity);
		int quan = product.getProudctQuantity()-quantity;
		if(product!=null) {
			
			/*
			 * total price calculation
			 * 
			 */
			
			double totalPrice = product.getProductPrice()*quantity;
			System.out.println(quan);
			/*
			 * random order id
			 */
			long orderid = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
			
			/*
			 * deliverd date
			 */
			LocalDate localDate = LocalDate.now();
			
			localDate=localDate.plusDays(3);
			
			productOrder.setOrderId(orderid);
			productOrder.setQuantity(quantity);
			productOrder.setProduct(product);
			productOrder.setPrice(totalPrice);
			productOrder.setUser(userDao.getUser());
			productOrder.setDeliverdDate(localDate);
			productOrder.setDeliveredAddress(address);
			
			/*
			 * 
			 */
			product.setProudctQuantity(quan);
			
			System.out.println("productQuantityfromtable = "+product.getProudctQuantity());
			productRepository.save(product);
			
			orderRepository.save(productOrder);
			
		}
		
		return null;
	}
}
