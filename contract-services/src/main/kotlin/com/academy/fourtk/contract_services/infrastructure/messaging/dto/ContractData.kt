package com.academy.fourtk.contract_services.infrastructure.messaging.dto

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.academy.fourtk.contract_services.repositories.mongo.adapter.ContractDocument
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ContractData(
    val contractId: String,
    val personId: String,
    val productId: String,
    val fullNamePerson: String? = null,
    val genderPerson: String? = null,
    val cpfPerson: String? = null,
    val birthdayAtPerson: String? = null,
    val nameProduct: String? = null,
    val descriptionProduct: String? = null,
    val quantityProduct: Int? = null,
    val originProduct: String? = null,
    val status: ContractStatusEnum,
    val integrationServiceAPendent: Boolean,
    val integrationServiceBPendent: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val canceledAt: LocalDateTime? = null,
)

fun builder(document: ContractDocument): ContractData = ContractData(
    contractId = document._id,
    personId = document.personId,
    productId = document.productId,
    fullNamePerson = document.fullNamePerson,
    genderPerson = document.personId,
    cpfPerson = document.cpfPerson,
    birthdayAtPerson = document.birthdayAtPerson,
    nameProduct = document.nameProduct,
    descriptionProduct = document.descriptionProduct,
    quantityProduct = document.quantityProduct,
    originProduct = document.originProduct,
    status = document.status,
    integrationServiceAPendent = document.integrationPersonServicePendent,
    integrationServiceBPendent = document.integrationProductServicePendent,
    createdAt = document.createdAt,
    updatedAt = document.updatedAt,
    canceledAt = document.canceledAt
)