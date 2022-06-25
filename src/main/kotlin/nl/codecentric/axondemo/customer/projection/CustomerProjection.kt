package nl.codecentric.axondemo.customer.projection

import nl.codecentric.axondemo.customer.CustomerRegisteredEvent
import nl.codecentric.axondemo.customer.AllCustomersQuery
import nl.codecentric.axondemo.customer.PasswordChangedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Repository

@Repository
@ProcessingGroup("customerRepository")
class CustomerProjection(private val customerRepository: CustomerRepository) {

    @EventHandler
    fun on(event: CustomerRegisteredEvent) {
        this.customerRepository.save(Customer(event.customerId, null))
    }

//    @EventHandler
//    fun on(event: PasswordChangedEvent) {
//        val copy = this.customerRepository.findById(event.customerId).get().copy(password = event.newPassword)
//        this.customerRepository.save(copy)
//    }

    @QueryHandler
    fun on(query: AllCustomersQuery): Iterable<Customer> {
        return customerRepository.findAll()
    }
}