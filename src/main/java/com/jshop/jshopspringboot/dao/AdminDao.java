package com.jshop.jshopspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.Admin;
import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.repository.AdminRepository;
import com.jshop.jshopspringboot.repository.ProductOwnerRepository;
import com.jshop.jshopspringboot.repository.ProductRepository;

/**
 * 
 * @author Ravi Kant Singh
 *
 *         we perform here sign-up and login code of admin
 */
@Repository
public class AdminDao {

	int adminId = 0;
	
	
	@Autowired
	private AdminRepository adminRepository;

	
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductOwnerRepository ownerRepository;

	@Autowired
	private ProductOwnerDao ownerDao;

	
	@Autowired
	private ProductRepository productRepository;
	
	/*
	 * signup code for admin saveMethod
	 */
	public Admin saveAdminDao(Admin admin) {

		return adminRepository.save(admin);
	}

	/*
	 * login with admin
	 */
	public Admin loginWithAdminDao(String email) {

		Admin admin = adminRepository.findByAdminEmail(email);

		if (admin != null) {
			adminId = admin.getAdminId();
//			System.out.println(adminId);
		}

		return admin;
	}

	/*
	 * getAllProductOwnerAdminDao
	 */
	public List<ProductOwner> getAllProductOwnerAdminDao() {

		return ownerDao.getAllProductOwnerDao();
	}

	/*
	 * getProductOwnerById
	 */
	public ProductOwner getProductOwnerByIdAdminDao(int productOwnerId) {

		return ownerDao.getProductOwnerById(productOwnerId);
	}

	/*
	 * verify product owner from no to yes and unverified from yes to no
	 */
	public ProductOwner verifyProductOwnerDao(int productOwnerId) {

		ProductOwner productOwner2 = getProductOwnerByIdAdminDao(productOwnerId);
		/*
		 * Get admin data by adminId
		 */
		Optional<Admin> optional = adminRepository.findById(adminId);
		Admin admin = null;
		if (optional.isPresent()) {
			admin = optional.get();
		}
		/*
		 * 
		 */
		if (productOwner2 != null) {

			if (productOwner2.getAdminVerify().equalsIgnoreCase("no")) {
				productOwner2.setAdminVerify("yes");
				productOwner2.setAdmin(admin);

			} else {
				admin = null;
				productOwner2.setAdmin(admin);
				productOwner2.setAdminVerify("no");
			}

			ownerRepository.save(productOwner2);
		}

		return productOwner2;
	}

	/*
	 * verify Product details By Admin
	 */
	public Product verifyProductDetailsByAdmin(int productId) {

		Product product = productDao.getProductByIdDao(productId);

		if (product != null) {

			if (product.getProductVerified().equalsIgnoreCase("no")) {
					
				product.setProductVerified("yes");
			}else {
				product.setProductVerified("no");
			}
		}
		
		productRepository.save(product);
		
		return product;
	}
}
