package com.academy.fourtk.contract_services.services.impl

import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ContractResponseV1
import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.repositories.mongo.adapter.entityToDocument
import com.academy.fourtk.contract_services.repositories.mongo.impl.ContractRepository
import com.academy.fourtk.contract_services.services.PersonService
import com.academy.fourtk.contract_services.services.ServiceContract
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ServiceContractImpl(
    private val repository: ContractRepository,
    private val personService: PersonService
    ) : ServiceContract {

    override fun create(entity: ContractEntity): ContractResponseV1 {

        //Salvando banco
        repository.save(entityToDocument(entity))

        //Buscar person
        val person = personService.getPersonById(entity.personId!!)

        //Buscando Objeto no banco
        val contract = repository.findByPersonId(entity.personId)

        //Atualizando contract

        val contractUpdated = contract.copy(
            integrationServiceAPendent = false,
            fullName = person.firstName + " " + person.lastName,
            gender = person.gender,
            cpf = person.cpf,
            birthdayAt = person.birthdayAt
        )

        repository.save(contractUpdated)



        return ContractResponseV1(
            numberContract = "123456",
            message = "Sucesso"
        )
    }
}