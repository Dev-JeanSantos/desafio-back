package com.academy.fourtk.contract_services.resources.gateways.products

import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "person-service",
    url = "\${gateway.person.services.url}"
)
interface ProductGateway {
    @GetMapping(
        value = ["person/{id}"]
    )
    fun getPersonById(
        @PathVariable("id") personId: String,
    ): PersonResponseData
}