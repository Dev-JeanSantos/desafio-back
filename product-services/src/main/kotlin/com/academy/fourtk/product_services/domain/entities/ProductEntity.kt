package com.academy.fourtk.product_services.domain.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ProductEntity(
    @JsonProperty("product_id")
    val productId: String ? = null,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("updatedA_at")
    val updateAt: LocalDateTime? = null
)