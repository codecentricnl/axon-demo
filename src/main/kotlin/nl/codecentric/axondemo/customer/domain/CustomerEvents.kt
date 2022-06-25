package nl.codecentric.axondemo.customer

data class CustomerRegisteredEvent(val customerId: String)
data class PasswordChangedEvent(val customerId: String, val newPassword: String)