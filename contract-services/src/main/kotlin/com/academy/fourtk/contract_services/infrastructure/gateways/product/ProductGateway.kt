package com.academy.fourtk.contract_services.infrastructure.gateways.product

import com.academy.fourtk.contract_services.infrastructure.gateways.product.dto.ProductResponseData
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "product-service",
    url = "\${gateway.product.services.url}"
)
interface ProductGateway {
    @GetMapping(
        value = ["product/{id}"]
    )
    fun getProductById(
        @PathVariable("id") productId: String
    ): ProductResponseData
}