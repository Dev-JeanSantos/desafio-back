package com.academy.fourtk.contract_services.repositories.mongo.adapter

import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.fasterxml.jackson.annotation.JsonFormat
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "services_contract")
data class ContractDocument (
    @BsonId
    val _id: String,
    val personId: String,
    val fullNamePerson: String? = null,
    val genderPerson: String? = null,
    val cpfPerson: String? = null,
    val birthdayAtPerson: String? = null,
    val productId: String,
    val nameProduct: String? = null,
    val descriptionProduct: String? = null,
    val quantityProduct: Int? = null,
    val originProduct: String? = null,
    val status: ContractStatusEnum,
    val integrationPersonServicePendent: Boolean,
    val integrationProductServicePendent: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val canceledAt: LocalDateTime? = null,
)
