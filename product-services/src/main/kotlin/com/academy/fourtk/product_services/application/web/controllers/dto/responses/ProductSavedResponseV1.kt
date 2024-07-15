package com.academy.fourtk.product_services.application.web.controllers.dto.responses

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductSavedResponseV1(
    @JsonProperty("product_id")
    val productId: String,
    @JsonProperty("created_at")
    val message: String
)
