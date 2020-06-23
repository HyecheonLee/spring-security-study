package com.hyecheon.springsecuritystudy.controller.login

import com.hyecheon.springsecuritystudy.domain.entity.Account
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {

	@GetMapping("/login")
	fun login(@RequestParam(required = false, value = "error") error: String?,
	          @RequestParam(required = false, value = "exception") exception: String?,
	          model: Model) = let {
		error?.let { model.addAttribute("error", it) }
		exception?.let { model.addAttribute("exception", it) }
		"login"
	}

	@GetMapping("/logout")
	fun logout(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse) =
			let {
				SecurityContextHolder.getContext().authentication
						?.let {
							SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, it)
						}
				"redirect:/login"
			}

	@GetMapping("/denied")
	fun accessPage(@RequestParam(value = "exception", required = false) exception: String?, model: Model) =
			let {
				val authentication = SecurityContextHolder.getContext().authentication
				val principal = authentication.principal
				if (principal is Account) {
					model.addAttribute("username", principal.username)
				}
				model.addAttribute("exception", exception)
				"user/login/denied"
			}
}