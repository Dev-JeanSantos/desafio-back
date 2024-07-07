package com.academy.fourtk.contract_services.application.web.controllers

import com.academy.fourtk.contract_services.application.web.controllers.builders.BuildContracts.buildContractEntity
import com.academy.fourtk.contract_services.application.web.controllers.builders.BuildContracts.buildResponse
import com.academy.fourtk.contract_services.application.web.controllers.dtos.requesties.ContractRequestV1
import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.services.ServiceContract
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contract")
class ContractController(
    val service: ServiceContract,
) {
    private val logger = KotlinLogging.logger {}
    @PostMapping
    fun createContract(
        @RequestBody contractRequestV1: ContractRequestV1,
    ): ResponseEntity<ContractResponseV1> {
        return runCatching {
            logger.info {
                "[CREATE-CONTRACT]-[Controller] Starting the contracted data:[$contractRequestV1]"
            }
            ResponseEntity(
                service.create(buildContractEntity(contractRequestV1)),
                HttpStatus.CREATED
            )
        }.onFailure {
            logger.error {
                "[CREATE-CONTRACT]-[Controller] failed"
            }
            buildResponse("123456", "Failed")
        }.onSuccess {
            logger.info {
                "[CREATE-CONTRACT]-[Controller] The hiring process was successfully completed"
            }
        }.getOrThrow()
    }
}
