package com.academy.fourtk.contract_services.application.web.handlers

import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ErrorResponse
import com.academy.fourtk.contract_services.domain.commons.ContractServiceIntegrationException
import com.academy.fourtk.contract_services.domain.commons.MongoException
import com.academy.fourtk.contract_services.domain.commons.NotFoundException
import com.academy.fourtk.contract_services.domain.commons.PersonServiceIntegrationException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(PersonServiceIntegrationException::class)
    fun handlePersonServiceIntegrationException(ex: PersonServiceIntegrationException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(ContractServiceIntegrationException::class)
    fun handleContractServiceIntegrationException(ex: ContractServiceIntegrationException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(MongoException::class)
    fun handleMongoException(ex: MongoException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun returnResponseError(
        message: String,
        httpStatus: HttpStatus
    ): ResponseEntity<Any?> {
        logger.error { message }
        return ResponseEntity(
            ErrorResponse(
                code = httpStatus.value().toString(),
                message = message
            ),
            httpStatus
        )
    }
}