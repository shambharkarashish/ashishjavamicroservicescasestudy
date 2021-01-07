package com.virtusa.ecommerce.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.model.UserAccount;
import com.virtusa.ecommerce.service.UserQueryService;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Timed
@Transactional(timeout = 1200)
public class UserQueryController {

	@Autowired
	private UserQueryService userQueryService;
	
	@GetMapping("/user/{userName}")
	public CompletableFuture<UserAccount> findById(@PathVariable("userName") String userName){
		return this.userQueryService.findById(userName);
	}
	
	@GetMapping("/user/{userName}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "userName") String userName){
		return this.userQueryService.listEventsForUsers(userName);
	}
}
