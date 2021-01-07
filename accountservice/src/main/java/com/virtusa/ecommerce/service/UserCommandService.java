package com.virtusa.ecommerce.service;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.command.CreateUserCommand;
import com.virtusa.ecommerce.dto.UserCreationDTO;
import com.virtusa.ecommerce.model.UserAccount;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCommandService {

	private final CommandGateway commandGateway;
	public CompletableFuture<UserAccount> createAccount(UserCreationDTO creationDTO){
		return this.commandGateway.send(new CreateUserCommand(
				creationDTO.getUserName(), creationDTO.getPassword()
				));
	}
}
