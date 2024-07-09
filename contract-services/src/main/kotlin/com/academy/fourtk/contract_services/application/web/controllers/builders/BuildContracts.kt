package com.academy.fourtk.contract_services.application.web.controllers.builders

import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.academy.fourtk.contract_services.application.web.controllers.dtos.requesties.ContractRequestV1
import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import java.time.LocalDateTime

object BuildContracts {
    fun buildContractEntity(contractRequestV1: ContractRequestV1) =
        ContractEntity(
            productId = contractRequestV1.productId,
            personId = contractRequestV1.personId,
            status = ContractStatusEnum.PENDING,
            integrationPersonServicePendent = true,
            integrationProductServicePendent = true,
            createdAt = LocalDateTime.now(),
        )

    fun buildResponse(
        numberContract: String,
        message: String,
    ) = ContractResponseV1(
        numberContract = numberContract,
        message = message,
    )
}
