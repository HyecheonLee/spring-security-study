package com.hyecheon.springsecuritystudy.security.handler

import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler
	: SimpleUrlAuthenticationSuccessHandler() {
	private val requestCache = HttpSessionRequestCache()
	private val redirectStrategy = DefaultRedirectStrategy()

	override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
		defaultTargetUrl = "/"
		val savedRequest = requestCache.getRequest(request, response)
		val targetUrl = savedRequest?.redirectUrl ?: defaultTargetUrl
		redirectStrategy.sendRedirect(request, response, targetUrl)
	}
}