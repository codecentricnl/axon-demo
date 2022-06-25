package nl.codecentric.axondemo.customer

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterCustomerCommand(val customerId: String)
data class ChangePasswordCommand(@TargetAggregateIdentifier val customerId: String, val newPassword: String)