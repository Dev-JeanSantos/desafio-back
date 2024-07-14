package com.academy.fourtk.contract_services.infrastructure.gateways.product.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ProductResponseData(
    @JsonProperty("product_id")
    val productId: String,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("update_at")
    val updateAt: LocalDateTime? = null
)