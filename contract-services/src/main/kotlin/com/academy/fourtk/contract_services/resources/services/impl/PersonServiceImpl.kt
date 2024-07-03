package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.resources.gateways.person.PersonGateway
import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.services.PersonService
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personGateway: PersonGateway
): PersonService {
    override fun getPersonById(personId: String): PersonResponseData {
       return personGateway.getPersonById(personId)
    }
}