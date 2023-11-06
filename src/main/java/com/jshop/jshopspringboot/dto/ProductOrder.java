package com.jshop.jshopspringboot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductOrder {

	@Id
	private long orderId;
	@UpdateTimestamp
	@Column(name = "bookingDate")
	private LocalDateTime bookingDateTime; 
	private LocalDate deliverdDate;
	private int quantity;
	private double price;
	private String deliveredAddress;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
}
