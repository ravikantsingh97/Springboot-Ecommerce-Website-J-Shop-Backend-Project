package com.jshop.jshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringboot.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserEmail(String userEmail);
}