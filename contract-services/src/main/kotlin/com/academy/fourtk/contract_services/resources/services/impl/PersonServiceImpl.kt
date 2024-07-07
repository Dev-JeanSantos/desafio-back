package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.domain.commons.NotFoundException
import com.academy.fourtk.contract_services.domain.commons.PersonServiceIntegrationException
import com.academy.fourtk.contract_services.resources.gateways.person.PersonGateway
import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.services.PersonService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personGateway: PersonGateway
): PersonService {

    private val logger = KotlinLogging.logger {}
    override fun getPersonById(personId: String): PersonResponseData {
        return runCatching {
            logger.info {
                "[CREATE-CONTRACT]-[Gateway] starting integration with service-person PersonId:[${personId}]"
            }
            personGateway.getPersonById(personId)
        }.onFailure {
            logger.error {
                "[CREATE-CONTRACT]-[Gateway] Integration Failure with PersonService"
            }
            throw PersonServiceIntegrationException("Integration Failure with PersonService")
        }.onFailure {
            logger.error {
                "[CREATE-CONTRACT]-[Gateway] No client exists with this personId: $personId."
            }
            throw NotFoundException("No client exists with this personId: $personId.")
        }.getOrThrow()
    }
}
