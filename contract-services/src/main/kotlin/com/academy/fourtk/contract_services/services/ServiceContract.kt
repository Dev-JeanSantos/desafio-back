package com.academy.fourtk.contract_services.services

import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1

interface ServiceContract {
    fun create(buildContractEntity: ContractEntity): ContractResponseV1

}
