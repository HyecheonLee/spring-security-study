package com.hyecheon.springsecuritystudy.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import com.hyecheon.springsecuritystudy.security.token.AjaxAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AjaxLoginProcessingFilter : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login", "POST")) {
	private val objectMapper = ObjectMapper()

	override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
		if (!isAjax(request)) {
			throw IllegalStateException("Authentication is not supported")
		}
		val accountDto = objectMapper.readValue<AccountDto>(request.reader)
		if (accountDto.username.isNullOrEmpty() || accountDto.password.isNullOrEmpty()) {
			throw IllegalArgumentException("Username or Password is empty")
		}
		val token = AjaxAuthenticationToken(accountDto.username ?: "", accountDto.password ?: "")
		return this.authenticationManager.authenticate(token)
	}

	private fun isAjax(request: HttpServletRequest): Boolean {
		return "XMLHttpRequest" == request.getHeader("X-Requested-With")
	}
}