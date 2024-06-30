package com.academy.fourtk.contract_services.builders

import com.academy.fourtk.contract_services.domain.ContractEntity
import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.academy.fourtk.contract_services.dtos.requesties.ContractRequestV1
import com.academy.fourtk.contract_services.dtos.responses.ContractResponseV1
import java.time.LocalDateTime

object BuildContracts {
    fun buildContractEntity(contractRequestV1: ContractRequestV1) =
        ContractEntity(
            productId = contractRequestV1.productId,
            personId = contractRequestV1.personId,
            status = ContractStatusEnum.PENDING,
            integrationServiceAPendent = true,
            integrationServiceBPendent = true,
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
