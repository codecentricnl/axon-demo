package nl.codecentric.axondemo.customer

import nl.codecentric.axondemo.customer.domain.CustomerAggregate
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class CustomerAggregateTest {
    private lateinit var fixture: FixtureConfiguration<CustomerAggregate>

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(CustomerAggregate::class.java)
    }

    @Test
    fun shouldHandleRegisterCustomerCommand() {
        fixture.givenNoPriorActivity()
            .`when`(RegisterCustomerCommand("someAccountId"))
            .expectEvents(CustomerRegisteredEvent("someAccountId"))
            .expectState { aggregate: CustomerAggregate ->
                assertEquals("someAccountId", aggregate.customerId)
            }
    }

    @Test
    fun shouldHandleChangePasswordCommand() {
        fixture.given(
            listOf(
                CustomerRegisteredEvent("Henk")
            )
        )
            .`when`(ChangePasswordCommand("Henk", "test123"))
            .expectEvents(PasswordChangedEvent("Henk", "test123"))
    }

    @Test
    fun shouldHandleNotChangePasswordWhenPasswordIsTheSame() {
        fixture.given(
            listOf(
                CustomerRegisteredEvent("Henk"),
                PasswordChangedEvent("Henk", "test123")
            )
        )
            .`when`(ChangePasswordCommand("Henk", "test123"))
            .expectNoEvents()
    }
}


























