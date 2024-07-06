package com.academy.fourtk.contract_services.infrastructure.messaging.mappers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule


val mapper = ObjectMapper().apply {
    registerModule(JavaTimeModule())
}
