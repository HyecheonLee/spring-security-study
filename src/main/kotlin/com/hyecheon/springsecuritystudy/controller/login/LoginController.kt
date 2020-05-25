package com.hyecheon.springsecuritystudy.controller.login

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {

	@GetMapping("/login")
	fun login() = "user/login/login"

	@GetMapping("/logout")
	fun logout(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse) =
			let {
				SecurityContextHolder.getContext().authentication
						?.let {
							SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, it)
						}
				"redirect:/login"
			}
}