package com.academy.fourtk.product_services.repositories.mongo

import com.academy.fourtk.product_services.repositories.mongo.adapter.ProductDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: MongoRepository<ProductDocument, String>