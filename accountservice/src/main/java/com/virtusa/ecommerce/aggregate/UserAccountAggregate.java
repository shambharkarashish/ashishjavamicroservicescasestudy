package com.virtusa.ecommerce.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.virtusa.ecommerce.command.CreateUserCommand;
import com.virtusa.ecommerce.event.UserCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class UserAccountAggregate {
	@AggregateIdentifier
	private String userName;
	private String password;
	
	@CommandHandler
	public UserAccountAggregate(CreateUserCommand command) {
		AggregateLifecycle.apply(
				new UserCreatedEvent(
					command.getUserName(),
					command.getPassword()
						)
				);
	}
	
	@EventSourcingHandler
	public void on(UserCreatedEvent event) {
		this.userName = event.getUserName();
		this.password = event.getPassword();
	}
}
