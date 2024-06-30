package com.academy.fourtk.contract_services.services

import com.academy.fourtk.contract_services.domain.ContractEntity
import com.academy.fourtk.contract_services.dtos.responses.ContractResponseV1

interface ServiceContract {
    fun create(buildContractEntity: ContractEntity): ContractResponseV1

}
