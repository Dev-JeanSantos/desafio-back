package com.academy.fourtk.contract_services.repositories.mongo.impl

import com.academy.fourtk.contract_services.repositories.mongo.adapter.ContractDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository: MongoRepository<ContractDocument, String> {
    fun findByPersonId(personId: String): ContractDocument
}
