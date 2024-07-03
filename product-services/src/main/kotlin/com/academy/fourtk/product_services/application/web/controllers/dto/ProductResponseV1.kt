package com.academy.fourtk.product_services.application.web.controllers.dto

import java.time.LocalDateTime

class ProductResponseV1(
    val productId: String,
    val name: String,
    val description: String,
    val quantity: Int,
    val origin: String,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)

