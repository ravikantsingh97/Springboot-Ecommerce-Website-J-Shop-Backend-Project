package com.jshop.jshopspringboot.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
@Component
public class User {
	
	@Id
	private int userId;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userPassword;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserCart> userCarts;
}

