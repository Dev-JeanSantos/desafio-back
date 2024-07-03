package com.academy.fourtk.contract_services

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@EnableFeignClients( "com.academy.fourtk.contract_services.resources.gateways.*")
@ComponentScan("com.academy.fourtk.*")
class ContractServicesApplication

fun main(args: Array<String>) {
	runApplication<ContractServicesApplication>(*args)
}
