package com.academy.fourtk.contract_services.services.impl

import com.academy.fourtk.contract_services.domain.ContractEntity
import com.academy.fourtk.contract_services.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.repositories.mongo.adapter.entityToDocument
import com.academy.fourtk.contract_services.repositories.mongo.impl.ContractRepository
import com.academy.fourtk.contract_services.services.ServiceContract
import org.springframework.stereotype.Service

@Service
class ServiceContractImpl(
     private val repository: ContractRepository
): ServiceContract {
    override fun create(entity: ContractEntity): ContractResponseV1 {
       repository.save(entityToDocument(entity))
        return ContractResponseV1(
            numberContract = "123456",
            message = "Sucesso"
        )
    }
}
