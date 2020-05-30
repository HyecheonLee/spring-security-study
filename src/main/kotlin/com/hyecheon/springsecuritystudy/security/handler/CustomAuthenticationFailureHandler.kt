package com.hyecheon.springsecuritystudy.security.handler

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationFailureHandler :
		SimpleUrlAuthenticationFailureHandler() {

	override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
		val errorMessage = when (exception) {
			is InsufficientAuthenticationException -> "Invalid Secret Key"
			is BadCredentialsException -> "Invalid Username or Password"
			else -> "Invalid Username or Password"
		}
		val redirectUrl = "/login?error=true&exception=${errorMessage}"
		setDefaultFailureUrl(redirectUrl)

		super.onAuthenticationFailure(request, response, exception)
	}
}