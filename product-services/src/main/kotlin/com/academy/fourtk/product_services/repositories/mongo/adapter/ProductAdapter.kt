package com.academy.fourtk.product_services.repositories.mongo.adapter

import com.academy.fourtk.product_services.application.web.controllers.dto.responses.ProductSavedResponseV1
import com.academy.fourtk.product_services.domain.entities.ProductEntity

fun entityToDocument(
    entity: ProductEntity
) = ProductDocument(
    nameProduct = entity.name,
    descriptionProduct = entity.description,
    originProduct = entity.origin,
    quantityProduct = entity.quantity,
    createdAt = entity.createdAt
)

fun documentToProductResponseV1(
    productDocument: ProductDocument
) = ProductSavedResponseV1(
    productId = productDocument._id,
    message = "Product created successfully."
)

