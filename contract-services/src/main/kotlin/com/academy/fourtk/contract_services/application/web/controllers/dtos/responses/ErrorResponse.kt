package com.academy.fourtk.contract_services.application.web.controllers.dtos.responses

data class ErrorResponse(
    val code: String,
    val message: String,
    val fields: MutableList<ErrorField>? = null
)

data class ErrorField(
    val field: String,
    val message: String,
    val value: String
)