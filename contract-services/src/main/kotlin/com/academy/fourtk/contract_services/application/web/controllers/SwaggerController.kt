package com.academy.fourtk.contract_services.application.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SwaggerController {
    @GetMapping("/docs")
    fun swaggerRedirect() = "redirect:/swagger-ui.html"
}