package com.academy.fourtk.contract_services.application.web.controllers.dtos.requesties

import com.fasterxml.jackson.annotation.JsonProperty

data class ContractRequestV1(
    @JsonProperty("person_id")
    val personId: String,
    @JsonProperty("product_id")
    val productId: String,
)
