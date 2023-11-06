package com.jshop.jshopspringboot.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ProductOwner{

	@Id
	@Column(name = "ownerid")
	private int productOwnerId;
	@Column(name = "ownername")
	private String productOwnerName;
	@Column(name = "owneremail")
	private String productOwnerEmail;
	@Column(name = "ownerpassword")
	private String productOwnerPassword;
	@Column(name = "adminverify")
	private String adminVerify;
	
	@ManyToOne
	private Admin admin;
	
	@OneToMany(mappedBy = "productOwner")
	@JsonIgnore
	private List<Product> products;
}

