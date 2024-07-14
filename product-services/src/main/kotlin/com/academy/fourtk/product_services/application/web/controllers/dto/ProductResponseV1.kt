package com.academy.fourtk.product_services.application.web.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class ProductResponseV1(
    @JsonProperty("product_id")
    val productId: String,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("updatedA_at")
    val updateAt: LocalDateTime
)

