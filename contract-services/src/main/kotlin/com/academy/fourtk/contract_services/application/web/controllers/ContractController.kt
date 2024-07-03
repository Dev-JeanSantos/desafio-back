package com.academy.fourtk.contract_services.application.web.controllers

import com.academy.fourtk.contract_services.application.web.controllers.builders.BuildContracts.buildContractEntity
import com.academy.fourtk.contract_services.application.web.controllers.builders.BuildContracts.buildResponse
import com.academy.fourtk.contract_services.application.web.controllers.dtos.requesties.ContractRequestV1
import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.services.ServiceContract
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contract")
class ContractController(
    val service: ServiceContract,
) {
    @PostMapping
    fun createContract(
        @RequestBody contractRequestV1: ContractRequestV1,
    ): ContractResponseV1 {
        return runCatching {
            service.create(buildContractEntity(contractRequestV1))
        }.onFailure {
            buildResponse("123456", "falha")
        }.getOrThrow()
    }
}
