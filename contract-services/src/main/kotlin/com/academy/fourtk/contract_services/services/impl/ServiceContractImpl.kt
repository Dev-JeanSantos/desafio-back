package com.academy.fourtk.contract_services.services.impl

import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.domain.enums.ContractStatusEnum
import com.academy.fourtk.contract_services.infrastructure.messaging.dto.builder
import com.academy.fourtk.contract_services.infrastructure.messaging.mappers.mapper
import com.academy.fourtk.contract_services.infrastructure.messaging.producer.SqsProducerService
import com.academy.fourtk.contract_services.repositories.mongo.adapter.entityToDocument
import com.academy.fourtk.contract_services.repositories.mongo.impl.ContractRepository
import com.academy.fourtk.contract_services.services.PersonService
import com.academy.fourtk.contract_services.services.ProductService
import com.academy.fourtk.contract_services.services.ServiceContract
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ServiceContractImpl(
    private val repository: ContractRepository,
    private val personService: PersonService,
    private val productService: ProductService,
    private val sqsProducerService: SqsProducerService
) : ServiceContract {
    override fun create(entity: ContractEntity): ContractResponseV1 {

        //Salvando banco
        val contractSaved = repository.save(entityToDocument(entity))

        //Buscar person
        val person = personService.getPersonById(entity.personId)

        //Buscando Objeto no banco
        val contract = repository.findByContractId(contractSaved._id)

        //Atualizando contract

        val personSaved = contract.copy(
            integrationServiceAPendent = false,
            status = ContractStatusEnum.ACTIVE,
            fullNamePerson = person.firstName + " " + person.lastName,
            genderPerson = person.gender,
            cpfPerson = person.cpf,
            birthdayAtPerson = person.birthdayAt,
            updatedAt = LocalDateTime.now()
        )

        val contractsaved = repository.save(personSaved)

        //Buscar person
        val product = productService.getProductById(entity.productId)


        val productUpdated = contractsaved.copy(
            integrationServiceBPendent = false,
            nameProduct = product.name,
            descriptionProduct = product.description,
            quantityProduct = product.quantity,
            originProduct = product.origin,
            updatedAt = LocalDateTime.now()
        )

        val contractFinalized = builder(repository.save(productUpdated))
        val message =  mapper.writeValueAsString(contractFinalized)
        sqsProducerService.sendMessage("contract-service-sqs", message)

        return ContractResponseV1(
            numberContract = contractFinalized.contractId,
            message = "Contrato Criado com Sucesso"
        )
    }
}