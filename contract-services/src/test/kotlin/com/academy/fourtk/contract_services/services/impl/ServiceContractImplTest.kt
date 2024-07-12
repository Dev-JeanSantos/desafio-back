package com.academy.fourtk.contract_services.services.impl

import com.academy.fourtk.contract_services.application.web.config.parser.toObject
import com.academy.fourtk.contract_services.domain.entities.ContractEntity
import com.academy.fourtk.contract_services.infrastructure.messaging.producer.SqsProducerService
import com.academy.fourtk.contract_services.repositories.mongo.adapter.ContractDocument
import com.academy.fourtk.contract_services.repositories.mongo.impl.ContractRepository
import com.academy.fourtk.contract_services.resources.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.resources.gateways.product.dto.ProductResponseData
import com.academy.fourtk.contract_services.services.PersonService
import com.academy.fourtk.contract_services.services.ProductService
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
class ServiceContractImplTest {

    @RelaxedMockK
    private lateinit var repository: ContractRepository

    @RelaxedMockK
    private lateinit var personService: PersonService

    @RelaxedMockK
    private lateinit var productService: ProductService

    @RelaxedMockK
    private lateinit var sqsService: SqsProducerService

    private lateinit var service: ServiceContractImpl

    @BeforeEach
    fun setup() {
        service = ServiceContractImpl(
            repository,
            personService,
            productService,
            sqsService,
            "name-sqs"
        )
    }

    @Test
    fun `DeveraCriarUmContratoAoPassarUmaRequestValidaERetornarUmCodigoComStatus201PersistindoContratoNaBaseDeDados`() {

        every { repository.save(any()) } returns savedContractDocument()
        every { personService.getPersonById(any()) } returns getPerson()
        every { productService.getProductById(any()) } returns getProduct()
        every { sqsService.sendMessage(any(), any()) } returns Unit

        val contract = service.create(createContract())


        assertNotNull(contract)
        assertEquals(savedContractDocument()._id, contract.numberContract)
        assertEquals("Contrato Criado com Sucesso", contract.message)

        verify(exactly = 3) { repository.save(any()) }
        verify(exactly = 1) { personService.getPersonById(any()) }
        verify(exactly = 1) { productService.getProductById(any()) }
        verify(exactly = 1) { sqsService.sendMessage(any(), any()) }
    }


    private fun createContract() = this::class.java.classLoader
        .getResource("json/contractEntity.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<ContractEntity>()

    private fun savedContractDocument() = this::class.java.classLoader
        .getResource("json/contractDocument.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<ContractDocument>()

    private fun getPerson() = this::class.java.classLoader
        .getResource("json/personResponseData.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<PersonResponseData>()

    private fun getProduct() = this::class.java.classLoader
        .getResource("json/productResponseData.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<ProductResponseData>()

}