package com.academy.fourtk.contract_services.resources.gateways.person.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class PersonResponseData(
    @JsonProperty("person_id")
    val personId: String,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    val gender: String,
    val cpf: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    @JsonProperty("birthday_at")
    val birthdayAt: String? = null
)
