package com.virtusa.ecommerce.controller;

import java.util.concurrent.CompletableFuture;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.dto.UserCreationDTO;
import com.virtusa.ecommerce.model.UserAccount;
import com.virtusa.ecommerce.service.UserCommandService;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Timed
@Transactional(timeout = 1200)
public class UserCommandController {
	@Autowired
	private UserCommandService userCommandService;
	
	@PostMapping("/useraccounts/v1.0")
	public CompletableFuture<UserAccount> addUserAccount(@RequestBody UserCreationDTO userCreationDTO){
		return userCommandService.createAccount(userCreationDTO);
	}
}
