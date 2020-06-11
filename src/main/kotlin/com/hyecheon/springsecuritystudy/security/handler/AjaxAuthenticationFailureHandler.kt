package com.hyecheon.springsecuritystudy.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxAuthenticationFailureHandler : AuthenticationFailureHandler {
	private val objectMapper = ObjectMapper()
	override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
		response.status = HttpStatus.UNAUTHORIZED.value()
		response.contentType = MediaType.APPLICATION_JSON_VALUE

		val errorMessage = when (exception) {
			is InsufficientAuthenticationException -> "Invalid Secret Key"
			is BadCredentialsException -> "Invalid Username or Password"
			else -> exception.message
		}
		objectMapper.writeValue(response.writer, errorMessage)
	}
}
