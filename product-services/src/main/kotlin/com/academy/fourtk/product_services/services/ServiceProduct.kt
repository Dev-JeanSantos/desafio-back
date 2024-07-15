package com.academy.fourtk.product_services.services

import com.academy.fourtk.product_services.application.web.controllers.dto.responses.ProductSavedResponseV1
import com.academy.fourtk.product_services.domain.entities.ProductEntity

interface  ServiceProduct {
    fun create(buildProductEntity: ProductEntity): ProductSavedResponseV1
}
