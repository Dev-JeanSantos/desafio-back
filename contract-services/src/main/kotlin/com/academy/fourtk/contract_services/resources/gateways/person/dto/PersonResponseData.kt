package com.academy.fourtk.contract_services.resources.gateways.person.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class PersonResponseData(
    val personId: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val cpf: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val birthdayAt: String? = null
)
