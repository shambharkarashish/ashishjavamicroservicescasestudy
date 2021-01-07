package com.virtusa.ecommerce.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;


import com.virtusa.ecommerce.model.UserAccount;
import com.virtusa.ecommerce.query.FindUserNameQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserQueryService {

	private QueryGateway queryGateway;
	private EventStore eventStore;
	
	public CompletableFuture<UserAccount> findById(String userName){
		return this.queryGateway.query(new FindUserNameQuery(userName), 
				ResponseTypes.instanceOf(UserAccount.class));
	}
	
	public List<Object> listEventsForUsers(String userName){
		return this.eventStore
				.readEvents(userName)
				.asStream()
				.map(Message::getPayload)
				.collect(Collectors.toList());
	}
}
