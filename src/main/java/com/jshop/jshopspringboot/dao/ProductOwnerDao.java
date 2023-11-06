package com.jshop.jshopspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.repository.ProductOwnerRepository;

@Repository
public class ProductOwnerDao {

	int productOwnerId=0;
	
	
	public int getProductOwnerId() {
		return productOwnerId;
	}

	public void setProductOwnerId(int productOwnerId) {
		this.productOwnerId = productOwnerId;
	}

	@Autowired
	private ProductOwnerRepository ownerRepository;

	/*
	 * productOwner Register
	 */
	public ProductOwner saveProductOwnerDao(ProductOwner productOwner) {

		productOwner.setAdminVerify("no");

		return ownerRepository.save(productOwner);
	}

	/*
	 * 
	 * getProductOwnerByEmail
	 */
	public ProductOwner getProductOwnerByEmail(String email) {

		ProductOwner owner= ownerRepository.findByProductOwnerEmail(email);
		
		if(owner!=null) {
			setProductOwnerId(owner.getProductOwnerId());
		}
		
		return owner;
	}

	
	/*
	 * getAllProductOwner
	 */
	public List<ProductOwner> getAllProductOwnerDao() {

		return ownerRepository.findAll();
	}
	
	/*
	 * getProductOwnerById
	 */
	public ProductOwner getProductOwnerById(int productOwnerId) {
		
		Optional<ProductOwner> optional=ownerRepository.findById(productOwnerId);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

}
