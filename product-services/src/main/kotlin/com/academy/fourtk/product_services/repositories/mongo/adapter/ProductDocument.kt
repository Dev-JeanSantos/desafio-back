package com.academy.fourtk.product_services.repositories.mongo.adapter

import com.fasterxml.jackson.annotation.JsonFormat
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "services_product")
data class ProductDocument(
    @BsonId
    val _id: String = UUID.randomUUID().toString(),
    val nameProduct: String,
    val descriptionProduct: String,
    val quantityProduct: Int,
    val originProduct: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
)
