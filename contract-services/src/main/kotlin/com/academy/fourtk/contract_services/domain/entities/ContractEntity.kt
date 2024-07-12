package com.academy.fourtk.contract_services.domain.entities

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ContractEntity(
    @JsonProperty("person_id")
    val personId: String,
    @JsonProperty("product_id")
    val productId: String,
    val status: ContractStatusEnum,
    @JsonProperty("integration_person_service_pendent")
    val integrationPersonServicePendent: Boolean,
    @JsonProperty("integration_product_service_pendent")
    val integrationProductServicePendent: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("update_at")
    val updatedAt: LocalDateTime? = null,
    @JsonProperty("canceled_at")
    val canceledAt: LocalDateTime? = null,
)
