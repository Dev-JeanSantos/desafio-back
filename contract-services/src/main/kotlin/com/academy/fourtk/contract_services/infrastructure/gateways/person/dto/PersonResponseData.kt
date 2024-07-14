package com.academy.fourtk.contract_services.infrastructure.gateways.person.dto

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
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("updatedA_at")
    val updatedAt: LocalDateTime? = null,
    @JsonProperty("birthday_at")
    val birthdayAt: String? = null
)
