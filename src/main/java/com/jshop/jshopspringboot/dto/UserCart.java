package com.jshop.jshopspringboot.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  cartId;
	private int productQuantity;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "productid")
	private Product product;
	
}