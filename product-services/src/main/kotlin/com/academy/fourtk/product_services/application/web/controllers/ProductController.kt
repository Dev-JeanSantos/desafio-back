package com.academy.fourtk.product_services.application.web.controllers

import com.academy.fourtk.product_services.application.web.controllers.builders.BuildsProducts.buildProductEntity
import com.academy.fourtk.product_services.application.web.controllers.builders.BuildsProducts.buildResponse
import com.academy.fourtk.product_services.application.web.controllers.dto.ProductResponseV1
import com.academy.fourtk.product_services.application.web.controllers.dto.requesties.ProductSavedRequestV1
import com.academy.fourtk.product_services.application.web.controllers.dto.responses.ProductSavedResponseV1
import com.academy.fourtk.product_services.services.ServiceProduct
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/product")
class ProductController(
    val service: ServiceProduct
) {

    private val logger = KotlinLogging.logger {}

    @PostMapping
    fun createProduct(
        @RequestBody productSavedRequestV1: ProductSavedRequestV1,
    ): ResponseEntity<ProductSavedResponseV1> {
        return runCatching {
            logger.info {
                "[CREATE-PRODUCT]-[Controller] Starting the saved product data:[$productSavedRequestV1]"
            }
            ResponseEntity(
                service.create(buildProductEntity(productSavedRequestV1)),
                HttpStatus.CREATED
            )
        }.onFailure {
            logger.error {
                "[CREATE-PRODUCT]-[Controller] failed"
            }
            buildResponse("123456", "Failed")
        }.onSuccess {
            logger.info {
                "[CREATE-PRODUCT]-[Controller] The hiring process was successfully completed"
            }
        }.getOrThrow()
    }


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