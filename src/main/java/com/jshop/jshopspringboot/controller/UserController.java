package com.jshop.jshopspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringboot.dto.User;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import com.jshop.jshopspringboot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	/*
	 * saveUserDao
	 */
	@PostMapping("/saveUserDetails")
	public ResponseStructure<User> saveUserController(@RequestBody User user) {
		
		return userService.saveUserService(user);
	}
	
	/*
	 * loginUser
	 */
	@GetMapping("/loginUser/{email}/{password}")
	public ResponseStructure<User> loginUserController(@PathVariable String email,@PathVariable String password)
	{
		return userService.loginUserService(email, password);
	}
}