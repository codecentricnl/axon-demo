package nl.codecentric.axondemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AxonDemoApplication

fun main(args: Array<String>) {
    runApplication<AxonDemoApplication>(*args)
}
