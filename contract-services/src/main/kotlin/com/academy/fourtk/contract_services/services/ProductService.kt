package com.academy.fourtk.contract_services.services

import com.academy.fourtk.contract_services.resources.gateways.product.dto.ProductResponseData

interface ProductService {
    fun getProductById(productId: String): ProductResponseData
}