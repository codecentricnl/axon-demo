package nl.codecentric.axondemo.customer.domain

import nl.codecentric.axondemo.customer.ChangePasswordCommand
import nl.codecentric.axondemo.customer.CustomerRegisteredEvent
import nl.codecentric.axondemo.customer.PasswordChangedEvent
import nl.codecentric.axondemo.customer.RegisterCustomerCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.EventHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class CustomerAggregate {
    @AggregateIdentifier
    lateinit var customerId: String
    var password: String? = null

    @CommandHandler
    constructor(command: RegisterCustomerCommand) {
        AggregateLifecycle.apply(CustomerRegisteredEvent(command.customerId))
    }

    @CommandHandler
    fun on(command: ChangePasswordCommand) {
        if(this.password != command.newPassword) {
            AggregateLifecycle.apply(PasswordChangedEvent(command.customerId, command.newPassword))
        }
    }

    @EventHandler
    fun handle(event: CustomerRegisteredEvent) {
        this.customerId = event.customerId
    }

    @EventHandler
    fun handle(event: PasswordChangedEvent) {
        this.password = event.newPassword
    }
}