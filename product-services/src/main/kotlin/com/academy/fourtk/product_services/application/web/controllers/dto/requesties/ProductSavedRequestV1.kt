package com.academy.fourtk.product_services.application.web.controllers.dto.requesties

data class ProductSavedRequestV1(
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String
)
