package com.academy.fourtk.contract_services.resources.gateways.product.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ProductResponseData(
    @JsonProperty("product_id")
    val productId: String,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("update_at")
    val updateAt: LocalDateTime
)