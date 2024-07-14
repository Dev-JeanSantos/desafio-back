package com.academy.fourtk.contract_services.resources.services.impl

import com.academy.fourtk.contract_services.application.web.config.parser.toObject
import com.academy.fourtk.contract_services.domain.commons.ContractServiceIntegrationException
import com.academy.fourtk.contract_services.infrastructure.gateways.product.ProductGateway
import com.academy.fourtk.contract_services.infrastructure.gateways.product.dto.ProductResponseData
import com.academy.fourtk.contract_services.infrastructure.gateways.impl.ProductServiceImpl
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class ProductServiceImplTest {

    @RelaxedMockK
    private lateinit var gateway: ProductGateway

    private lateinit var service: ProductServiceImpl

    @BeforeEach
    fun setup() {
        service = ProductServiceImpl(
            gateway
        )
    }

    @Test
    fun DeveraRetornarUmProductComSucessoAoPassarUmaPersonIdValidoEExistenteRetornandoUmCodigoComStatus201() {

        every { gateway.getProductById(any()) } returns getProduct()

        val product = service.getProductById(getProduct().productId)

        kotlin.test.assertNotNull(product)
        kotlin.test.assertEquals(getProduct().productId, product.productId)
        kotlin.test.assertEquals(getProduct().name, product.name)
        kotlin.test.assertEquals(getProduct().origin, product.origin)
        kotlin.test.assertEquals(getProduct().description, product.description)
        kotlin.test.assertEquals(getProduct().quantity, product.quantity)

        verify(exactly = 1) { gateway.getProductById(any()) }
    }

    @Test
    fun DeveraRetornarProductServiceIntegrationExceptionQuandoAApiEstiverIndisponivel() {

        every { gateway.getProductById(any()) } throws ContractServiceIntegrationException("Integration Failure with PersonService")

        org.junit.jupiter.api.assertThrows<ContractServiceIntegrationException> {
            service.getProductById(getProduct().productId)
        }
    }


    private fun getProduct() = this::class.java.classLoader
        .getResource("json/productResponseData.json")
        ?.readBytes()!!.toString(Charsets.UTF_8).toObject<ProductResponseData>()
}