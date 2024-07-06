package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.resources.gateways.product.ProductGateway
import com.academy.fourtk.contract_services.resources.gateways.product.dto.ProductResponseData
import com.academy.fourtk.contract_services.services.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productGateway: ProductGateway
): ProductService {
    override fun getProductById(productId: String): ProductResponseData {
       return productGateway.getProductById(productId)
    }
}