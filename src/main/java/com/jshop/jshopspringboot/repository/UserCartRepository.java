package com.jshop.jshopspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringboot.dto.UserCart;

public interface UserCartRepository extends JpaRepository<UserCart, Integer> {

}

