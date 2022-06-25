package nl.codecentric.axondemo.customer

import nl.codecentric.axondemo.customer.projection.Customer
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType
import org.axonframework.queryhandling.QueryGateway
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.concurrent.CompletableFuture

@Controller
class CustomerController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    @QueryMapping
    fun customers(): CompletableFuture<MutableList<Customer>> {
        return queryGateway.query(
            AllCustomersQuery(""),
            MultipleInstancesResponseType(Customer::class.java))
    }

    @MutationMapping
    fun registerCustomer(@Argument command: RegisterCustomerCommand): String {
        commandGateway.sendAndWaitForResponse(command)
        return "accepted"
    }

    @MutationMapping
    fun changePassword(@Argument command: ChangePasswordCommand) : String {
        commandGateway.sendAndWaitForResponse(command)
        return "accepted"
    }

    fun <T> CommandGateway.sendAndWaitForResponse(command: T) {
        try {
            sendAndWait<T>(command)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}

