package com.academy.fourtk.contract_services.application.web.controllers.dtos.responses

import com.fasterxml.jackson.annotation.JsonProperty

data class ContractResponseV1(
    @JsonProperty("number_contract")
    val numberContract: String,
    val message: String,
)
