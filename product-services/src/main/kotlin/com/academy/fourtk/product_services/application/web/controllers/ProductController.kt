package com.academy.fourtk.product_services.application.web.controllers

import com.academy.fourtk.product_services.application.web.controllers.dto.ProductResponseV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/product")
class ProductController {

    @GetMapping("/{product_id}")
    fun findProductByProductId(
        @PathVariable("product_id") personId: String
    ): ProductResponseV1 {
        val product = ProductResponseV1(
            productId = "P123456",
            name = "Produto Exemplo",
            description = "Descrição do Produto Exemplo",
            quantity = 10,
            origin = "Brasil",
            createdAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now()
        )
        return runCatching {
            println("Executou product-services")
            product
        }.onFailure {
            "FALHA"
        }.getOrThrow()
    }
}