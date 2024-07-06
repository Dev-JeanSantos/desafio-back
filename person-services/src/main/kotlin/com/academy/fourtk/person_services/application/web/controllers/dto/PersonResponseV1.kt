package com.academy.fourtk.person_services.application.web.controllers.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class PersonResponseV1(
    val personId: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val cpf: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    val birthdayAt: LocalDate,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime,
)
