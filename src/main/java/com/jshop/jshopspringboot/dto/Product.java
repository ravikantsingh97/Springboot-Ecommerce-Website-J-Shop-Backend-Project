package com.jshop.jshopspringboot.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Product {

	@Id
	private int productId;
	private String productName;
	private double productPrice;
	private String productBrand;
	private String productVerified;
	private int proudctQuantity;
	
	@ManyToOne
	@JoinColumn(name="ownerid")
	private ProductOwner productOwner;
	
	
}
