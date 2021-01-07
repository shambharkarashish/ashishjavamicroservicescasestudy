package com.virtusa.ecommerce.projection;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.virtusa.ecommerce.event.UserCreatedEvent;
import com.virtusa.ecommerce.model.UserAccount;
import com.virtusa.ecommerce.query.FindUserNameQuery;
import com.virtusa.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserAccountProjection {

	@Autowired
	private final UserRepository userRepository;
	
	@EventHandler 
	public void on(UserCreatedEvent event) {
		log.debug("Handling a User Account Creation command {}",event.getUserName());
		
		  UserAccount userAccount = new UserAccount(
	                event.getUserName(),event.getPassword());
	        this.userRepository.save(userAccount);
	}
	

    @QueryHandler
    public UserAccount handle(FindUserNameQuery query) {
        log.debug("Handling FindBankAccountQuery query: {}", query);
        return this.userRepository.findById(query.getUserName()).orElse(null);
    }
}
