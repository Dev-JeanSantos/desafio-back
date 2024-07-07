package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.domain.commons.ContractServiceIntegrationException
import com.academy.fourtk.contract_services.domain.commons.NotFoundException
import com.academy.fourtk.contract_services.resources.gateways.product.ProductGateway
import com.academy.fourtk.contract_services.resources.gateways.product.dto.ProductResponseData
import com.academy.fourtk.contract_services.services.ProductService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productGateway: ProductGateway
) : ProductService {

    private val logger = KotlinLogging.logger {}
    override fun getProductById(productId: String): ProductResponseData {
        return runCatching {
            logger.info {
                "[CREATE-CONTRACT]-[Gateway] starting integration with service-product ProductId:[${productId}]"
            }
            productGateway.getProductById(productId)
        }.onFailure {
            logger.error {
                "[CREATE-CONTRACT]-[Gateway] Integration Failure with ProductService"
            }
            throw ContractServiceIntegrationException("Integration Failure with ContractService")
        }.onFailure {
            logger.error {
                "[CREATE-CONTRACT]-[Gateway] No product exists with this productId: $productId."
            }
            throw NotFoundException("No client exists with this productId: $productId.")
        }.getOrThrow()
    }
}