package com.puj.admincenter.controller

import com.puj.admincenter.dto.login.LoginDto
import com.puj.admincenter.service.LoginService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RequestMapping("/login")
@RestController
class LoginController(val loginService: LoginService) {
    @PostMapping(
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun login(@RequestBody(required = true) @Valid loginDto: LoginDto): ResponseEntity<*>
        = loginService.login(loginDto)
}