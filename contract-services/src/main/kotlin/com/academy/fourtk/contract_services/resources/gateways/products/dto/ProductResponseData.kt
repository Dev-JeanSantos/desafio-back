package com.academy.fourtk.contract_services.resources.gateways.products.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class ProductResponseData(
    val personId: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val cpf: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val birthdayAt: LocalDate? = null
)
