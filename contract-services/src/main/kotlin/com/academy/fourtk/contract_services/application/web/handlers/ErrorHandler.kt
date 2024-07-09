package com.academy.fourtk.contract_services.application.web.handlers

import com.academy.fourtk.contract_services.application.web.controllers.dtos.responses.ErrorResponse
import com.academy.fourtk.contract_services.domain.commons.*
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ErrorHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(PersonServiceIntegrationException::class)
    fun handlePersonServiceIntegrationException(ex: PersonServiceIntegrationException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NoContentException::class)
    fun handleNoContentException(ex: NoContentException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.NO_CONTENT)
    }
    @ExceptionHandler(UnauthorizationException::class)
    fun handleUnauthorizationException(ex: UnauthorizationException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<Any?> {
        return returnResponseError(ex.message!!, HttpStatus.UNPROCESSABLE_ENTITY)
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

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        we: WebRequest
    ): ResponseEntity<ApiError> {
        val errors =
            ex.bindingResult.fieldErrors.map {
                fieldError -> "${fieldError.field}: ${fieldError.defaultMessage}"
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError(HttpStatus.BAD_REQUEST.value().toString(), errors))
    }

    data class ApiError(
        val code: String,
        val errors: List<String>
    ){
        constructor(code: String, error:String) : this(code, listOf(error))
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