package com.academy.fourtk.contract_services.domain

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import java.time.LocalDateTime

data class ContractEntity(
    val personId: String,
    val productId: String,
    val status: ContractStatusEnum,
    val integrationServiceAPendent: Boolean,
    val integrationServiceBPendent: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val canceledAt: LocalDateTime? = null,
)
