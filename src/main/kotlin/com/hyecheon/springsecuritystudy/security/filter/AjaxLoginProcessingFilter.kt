package com.hyecheon.springsecuritystudy.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hyecheon.springsecuritystudy.dto.AccountDto
import com.hyecheon.springsecuritystudy.security.token.AjaxAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.lang.IllegalArgumentException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxLoginProcessingFilter : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login")) {
	private val objectMapper = ObjectMapper()

	@Throws(Exception::class)
	override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
		if (!isAjax(request)) {
			throw IllegalStateException("Authentication is not supported")
		}
		val accountDto = objectMapper.readValue<AccountDto>(request.reader)
		if (accountDto.username.isNullOrEmpty() || accountDto.password.isNullOrEmpty()) {
			throw IllegalArgumentException("Username or Password is empty")
		}
		val ajaxAuthenticationToken = AjaxAuthenticationToken(accountDto.username, accountDto.password)
		return authenticationManager.authenticate(ajaxAuthenticationToken)
	}

	private fun isAjax(request: HttpServletRequest): Boolean {
		return "XMLHttpRequest" == request.getHeader("X-Requested-With")
	}
}