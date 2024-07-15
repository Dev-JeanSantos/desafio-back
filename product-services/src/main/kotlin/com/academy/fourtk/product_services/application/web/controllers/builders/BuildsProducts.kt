package com.academy.fourtk.product_services.application.web.controllers.builders

import com.academy.fourtk.product_services.application.web.controllers.dto.requesties.ProductSavedRequestV1
import com.academy.fourtk.product_services.application.web.controllers.dto.responses.ProductSavedResponseV1
import com.academy.fourtk.product_services.domain.entities.ProductEntity
import java.time.LocalDateTime

object BuildsProducts {
    fun buildProductEntity(product: ProductSavedRequestV1) =
        ProductEntity(
            name = product.name,
            description = product.description,
            origin = product.origin,
            quantity = product.quantity,
            createdAt = LocalDateTime.now()
        )

    fun buildResponse(
        productId: String,
        message: String,
    ) = ProductSavedResponseV1(
        productId = productId,
        message = message
    )
}