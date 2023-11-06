package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.UserDao;
import com.jshop.jshopspringboot.dto.User;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ResponseStructure<User> responseStructure;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HttpSession httpSession;
	/*
	 * saveUserDao
	 */
	public ResponseStructure<User> saveUserService(User user) {
		
		String password = adminService.validatePassword(user.getUserPassword());
		String email =  adminService.validateEmail(user.getUserEmail());
		
		if (email != null) {

			if (password != null) {
				
				User user1 = userDao.saveUserDao(user);

				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("User----Registered");
				responseStructure.setDescription("congratulation---Please--Login");
				responseStructure.setData(user1);
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("Check--Your---Password");
				responseStructure.setDescription(
						"your password length should be 8 character along with 1 lower case,1 upperCase,atleast 1 special Character(!@#$*&%),1 number");
				responseStructure.setData(null);
			}

		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Check--Your---Email");
			responseStructure.setDescription("your email should contain atleast alphabetnumber@hgja.com");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
	
	/*
	 * loginUser
	 */
	public ResponseStructure<User> loginUserService(String email, String password)
	{
		User user = userDao.loginUserDao(email);
		
		if(user!=null) {
			System.out.println(user.getUserPassword());
			System.out.println("Password = "+password);
			if(password.equals(user.getUserPassword())) {
				
				httpSession.setAttribute("password", user.getUserPassword());
				httpSession.setMaxInactiveInterval(200);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("login-success");
				responseStructure.setDescription("you have logged in....please perform your operation");
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("login credentials failed");
				responseStructure.setDescription("please checck your password...and type correctly");
			}	
		}else {
			responseStructure.setStatusMsg("login credentials failed");
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setDescription("please checck your email...and type correctly");
		}
		return responseStructure;
	}
}
