package com.academy.fourtk.product_services.services.impl

import com.academy.fourtk.product_services.application.web.controllers.dto.responses.ProductSavedResponseV1
import com.academy.fourtk.product_services.domain.entities.ProductEntity
import com.academy.fourtk.product_services.repositories.mongo.ProductRepository
import com.academy.fourtk.product_services.repositories.mongo.adapter.documentToProductResponseV1
import com.academy.fourtk.product_services.repositories.mongo.adapter.entityToDocument
import com.academy.fourtk.product_services.services.ServiceProduct
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ServiceProductImpl(
    private val repository: ProductRepository
) : ServiceProduct {

    private val logger = KotlinLogging.logger {}
    override fun create(entity: ProductEntity): ProductSavedResponseV1 {
      return runCatching {
           "[CREATE-PRODUCT]-[Service] Starting create product " +
                   "Name prodcut:[${entity.name}]"
         val productSaved = repository.save(entityToDocument(entity))
          documentToProductResponseV1(productSaved)
       }.onFailure {

       }.getOrThrow()
    }
}