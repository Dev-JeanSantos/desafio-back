package com.academy.fourtk.person_services

import com.academy.fourtk.person_services.dto.PersonResponseV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime


@RestController
@RequestMapping("/person")
class PersonController {
    @GetMapping("/{person_id}")
    fun findPersonByPersonId(
        @PathVariable("person_id") personId: String
    ): PersonResponseV1 {

        val person = PersonResponseV1(
            personId = "123e4567-e89b-12d3-a456-426614174000",
            firstName = "João",
            lastName = "Silva",
            gender = "Masculino",
            cpf ="123.456.789-00",
            birthdayAt = LocalDate.of(1990,5 ,15),
            createdAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now()
        )
        return runCatching {
            person
        }.onFailure {
            "FALHA"
        }.getOrThrow()
    }

}