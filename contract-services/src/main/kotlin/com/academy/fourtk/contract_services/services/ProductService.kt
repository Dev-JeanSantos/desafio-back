package com.academy.fourtk.contract_services.services

import com.academy.fourtk.contract_services.infrastructure.gateways.product.dto.ProductResponseData

interface ProductService {
    fun getProductById(productId: String): ProductResponseData
}