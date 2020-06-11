package com.hyecheon.springsecuritystudy.security.provider

import com.hyecheon.springsecuritystudy.security.service.AccountContext
import com.hyecheon.springsecuritystudy.security.token.AjaxAuthenticationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder


class AjaxAuthenticationProvider : AuthenticationProvider {
	@Autowired
	lateinit var userDetailsService: UserDetailsService

	@Autowired
	lateinit var passwordEncoder: PasswordEncoder

	override fun authenticate(authentication: Authentication): Authentication {
		val username = authentication.name
		val password = authentication.credentials as String

		val accountContext = userDetailsService.loadUserByUsername(username) as AccountContext

		if (!passwordEncoder.matches(password, accountContext.account.password)) {
			throw BadCredentialsException("BadCredentialsException")
		}

		return AjaxAuthenticationToken(accountContext.account, null, accountContext.authorities)
	}

	override fun supports(authentication: Class<*>): Boolean {
		return authentication == AjaxAuthenticationToken::class
	}

}