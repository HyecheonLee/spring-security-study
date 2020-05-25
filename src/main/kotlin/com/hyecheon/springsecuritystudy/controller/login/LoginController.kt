package com.hyecheon.springsecuritystudy.controller.login

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {

	@GetMapping("/login")
	fun login() = "user/login/login"

	@GetMapping("/logout")
	fun logout(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): String {
		val authentication = SecurityContextHolder.getContext().authentication
		if (authentication != null) {
			SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication)
		}
		return "redirect:/login"
	}
}