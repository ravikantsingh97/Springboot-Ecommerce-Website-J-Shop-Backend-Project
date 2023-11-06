package com.jshop.jshopspringboot.responsestructure;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {

	private int statusCode;
	private String statusMsg;
	private String description;
	private T data;
}
