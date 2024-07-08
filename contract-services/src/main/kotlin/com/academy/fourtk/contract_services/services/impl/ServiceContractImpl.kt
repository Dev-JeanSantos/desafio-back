package com.academy.fourtk.contract_services.services.impl

import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.domain.commons.NotFoundException
import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.academy.fourtk.contract_services.infrastructure.messaging.dto.builder
import com.academy.fourtk.contract_services.infrastructure.messaging.mappers.mapper
import com.academy.fourtk.contract_services.infrastructure.messaging.producer.SqsProducerService
import com.academy.fourtk.contract_services.repositories.mongo.adapter.ContractDocument
import com.academy.fourtk.contract_services.repositories.mongo.adapter.entityToDocument
import com.academy.fourtk.contract_services.repositories.mongo.impl.ContractRepository
import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.resources.gateways.product.dto.ProductResponseData
import com.academy.fourtk.contract_services.services.PersonService
import com.academy.fourtk.contract_services.services.ProductService
import com.academy.fourtk.contract_services.services.ServiceContract
import com.mongodb.MongoTimeoutException
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ServiceContractImpl(
    private val repository: ContractRepository,
    private val personService: PersonService,
    private val productService: ProductService,
    private val sqsProducerService: SqsProducerService,
    @Value("\${cloud.aws.endpoint.queue}")
    private val queue: String
) : ServiceContract {

    private val logger = KotlinLogging.logger {}
    override fun create(entity: ContractEntity): ContractResponseV1 {
        try {
            logger.info {
                "[CREATE-CONTRACT]-[Service] Starting the contracted process by " +
                        "PersonId:[${entity.personId}] ProductId:[${entity.productId}]"
            }
            val contractSaved = repository.save(entityToDocument(entity))
            logger.info {
                "[CREATE-CONTRACT]-[Mongo] Contract saved in the database success " +
                        "by Id:[${contractSaved._id}] e Status:[${contractSaved.status}]"
            }
            val person = personService.getPersonById(entity.personId)
            logger.info {
                "[CREATE-CONTRACT]-[Gateway] successfully completed integration with service-person," +
                        "data:[${person}]"
            }
            try {
                val contract = repository.findByContractId(contractSaved._id)

                val contractPerson = repository.save(contractDocumentByPerson(contract, person))
                logger.info {
                    "[CREATE-CONTRACT]-[Mongo] Contract and Person saved in the database success " +
                            "by Id:[${contractPerson._id}] and " +
                            "there is an integration issue with the product service person:[${contractSaved.integrationServiceAPendent}]"
                }

                logger.info {
                    "[CREATE-CONTRACT]-[Gateway] successfully completed integration with service-product" +
                            "data:[${person}]"
                }
                val product = productService.getProductById(entity.productId)
                val contractFinalized = builder(repository.save(contractDocumentByProduct(contractPerson, product)))

                logger.info {
                    "[CREATE-CONTRACT]-[Mongo] Contract and Product saved in the database success " +
                            "by Id:[${contractSaved._id}] and " +
                            "there is an integration issue with the product service:[${contractFinalized.integrationServiceBPendent}]"
                }

                logger.info {
                    "[CREATE-CONTRACT]-[Mongo]Contract saved completely successfully" +
                            "by Id:[${contractSaved._id}] and " +
                            "Status:[${contractSaved.status}]"
                }

                val message = mapper.writeValueAsString(contractFinalized)
                sqsProducerService.sendMessage(queue, message)
                logger.info {
                    "[CREATE-CONTRACT]-[SQS]Contract sent to SQS successfully by message:[${message}]"
                }

                return ContractResponseV1(
                    numberContract = contractFinalized.contractId,
                    message = "Contrato Criado com Sucesso"
                )

            } catch (e: NotFoundException) {
                throw NotFoundException("Contrato nâo encontrado no banco")
            }

        } catch (e: MongoTimeoutException) {
            throw MongoTimeoutException("Falha no mongo")
        }
    }

    private fun contractDocumentByProduct(
        contractSaved: ContractDocument,
        product: ProductResponseData
    ) = contractSaved.copy(
        integrationServiceBPendent = false,
        nameProduct = product.name,
        descriptionProduct = product.description,
        quantityProduct = product.quantity,
        originProduct = product.origin,
        updatedAt = LocalDateTime.now()
    )

    private fun contractDocumentByPerson(
        contract: ContractDocument,
        person: PersonResponseData
    ): ContractDocument {
        val personSaved = contract.copy(
            integrationServiceAPendent = false,
            status = ContractStatusEnum.ACTIVE,
            fullNamePerson = person.firstName + " " + person.lastName,
            genderPerson = person.gender,
            cpfPerson = person.cpf,
            birthdayAtPerson = person.birthdayAt,
            updatedAt = LocalDateTime.now()
        )
        return personSaved
    }
}