package com.academy.fourtk.contract_services.repositories.mongo.impl

import com.academy.fourtk.contract_services.repositories.mongo.adapter.ContractDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository: MongoRepository<ContractDocument, String> {
    @Query("{ '_id' : ?0 }")
    fun findByContractId(_id: String): ContractDocument
}
