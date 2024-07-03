package com.academy.fourtk.contract_services.resources.gateways.product.dto

import java.time.LocalDateTime

data class ProductResponseData(
    val productId: String,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)