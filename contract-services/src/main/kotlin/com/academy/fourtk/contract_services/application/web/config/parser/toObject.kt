package com.academy.fourtk.contract_services.application.web.config.parser

import com.fasterxml.jackson.databind.PropertyNamingStrategy

inline fun <reified T : Any> String.toObject(
    propertyNameStrategy: PropertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
): T =
    JsonParserBuilder.default()
        .setPropertyNamingStrategy(propertyNameStrategy)
        .readValue(this, T::class.java)