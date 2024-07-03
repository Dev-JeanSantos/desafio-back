package com.academy.fourtk.contract_services.services

import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData

interface PersonService {
    fun getPersonById(personId: String): PersonResponseData
}