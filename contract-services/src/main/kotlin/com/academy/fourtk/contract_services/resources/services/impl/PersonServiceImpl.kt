package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.domain.commons.NotFoundException
import com.academy.fourtk.contract_services.domain.commons.PersonServiceIntegrationException
import com.academy.fourtk.contract_services.resources.gateways.person.PersonGateway
import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.services.PersonService
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personGateway: PersonGateway
): PersonService {
    override fun getPersonById(personId: String): PersonResponseData {
        return runCatching {
            personGateway.getPersonById(personId)
        }.onFailure {
            throw PersonServiceIntegrationException("Integration Failure with PersonService")
        }.onFailure {
            throw NotFoundException("No client exists with this personId: $personId.")
        }.getOrThrow()
    }
}
