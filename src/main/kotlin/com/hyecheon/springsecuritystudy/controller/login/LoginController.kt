package com.hyecheon.springsecuritystudy.controller.login

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

	@GetMapping("/login")
	fun login() = "user/login/login"
}