package com.academy.fourtk.contract_services.repositories.mongo.adapter

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "contract")
data class ContractDocument (
    @BsonId
    val _id: String?,
    val personId: String,
    val fullName: String? = null,
    val gender: String? = null,
    val cpf: String? = null,
    val birthdayAt: LocalDate? = null,
    val productId: String,
    val status: ContractStatusEnum,
    val integrationServiceAPendent: Boolean,
    val integrationServiceBPendent: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val canceledAt: LocalDateTime? = null,
)
