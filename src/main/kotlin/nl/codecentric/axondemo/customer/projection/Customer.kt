package nl.codecentric.axondemo.customer.projection

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Customer(
    @Id val customerId: String,
    var password: String?
)