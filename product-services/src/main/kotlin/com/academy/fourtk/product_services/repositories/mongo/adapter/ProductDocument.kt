package com.academy.fourtk.product_services.repositories.mongo.adapter

import com.fasterxml.jackson.annotation.JsonFormat
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "services_product")
data class ProductDocument(

    @BsonId
    val _id: String = generateProductId(),
    val nameProduct: String,
    val descriptionProduct: String,
    val quantityProduct: Int,
    val originProduct: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
)


fun generateProductId(): String {
    val randomInt = (100..999).random()
    return "PRODUCT_$randomInt"
}