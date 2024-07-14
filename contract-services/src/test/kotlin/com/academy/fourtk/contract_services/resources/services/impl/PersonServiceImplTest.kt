package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.application.web.config.parser.toObject
import com.academy.fourtk.contract_services.domain.commons.PersonServiceIntegrationException
import com.academy.fourtk.contract_services.infrastructure.gateways.person.PersonGateway
import com.academy.fourtk.contract_services.infrastructure.gateways.person.dto.PersonResponseData
import com.academy.fourtk.contract_services.infrastructure.gateways.impl.PersonServiceImpl
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(SpringExtension::class)
class PersonServiceImplTest {

    @RelaxedMockK
    private lateinit var gateway: PersonGateway

    private lateinit var service: PersonServiceImpl

    @BeforeEach
    fun setup() {
        service = PersonServiceImpl(
            gateway
        )
    }

    @Test
    fun DeveraRetornarUmPersonComSucessoAoPassarUmaPersonIdValidoEExistenteRetornandoUmCodigoComStatus201() {

        every { gateway.getPersonById(any()) } returns getPerson()

        val person = service.getPersonById(getPerson().personId)

        assertNotNull(person)
        assertEquals(getPerson().personId, person.personId)
        assertEquals(getPerson().cpf, person.cpf)
        assertEquals(getPerson().birthdayAt, person.birthdayAt)
        assertEquals(getPerson().gender, person.gender)
        assertEquals(getPerson().firstName, person.firstName)
        assertEquals(getPerson().lastName, person.lastName)

        verify(exactly = 1) { gateway.getPersonById(any()) }
    }

    @Test
    fun DeveraRetornarPersonServiceIntegrationExceptionQuandoAApiEstiverIndisponivel() {

        every { gateway.getPersonById(any()) } throws PersonServiceIntegrationException("Integration Failure with PersonService")

        assertThrows<PersonServiceIntegrationException> {
            service.getPersonById(getPerson().personId)
        }
    }


    private fun getPerson() = this::class.java.classLoader
        .getResource("json/personResponseData.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<PersonResponseData>()
}


