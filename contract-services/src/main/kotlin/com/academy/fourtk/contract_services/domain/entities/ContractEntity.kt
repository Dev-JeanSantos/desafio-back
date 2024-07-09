package com.academy.fourtk.contract_services.domain.entities

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ContractEntity(
    val personId: String,
    val productId: String,
    val status: ContractStatusEnum,
    val integrationPersonServicePendent: Boolean,
    val integrationProductServicePendent: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val canceledAt: LocalDateTime? = null,
)
