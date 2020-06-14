package com.hyecheon.springsecuritystudy.security.config

import com.hyecheon.springsecuritystudy.security.filter.AjaxLoginProcessingFilter
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.HttpSecurityBuilder
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher


class AjaxLoginConfigurer<H : HttpSecurityBuilder<H>> :
		AbstractAuthenticationFilterConfigurer<H, AjaxLoginConfigurer<H>, AjaxLoginProcessingFilter>(
				AjaxLoginProcessingFilter(), null) {

	private var authenticationManager: AuthenticationManager? = null
	private var successHandler: AuthenticationSuccessHandler? = null
	private var failureHandler: AuthenticationFailureHandler? = null

	override fun createLoginProcessingUrlMatcher(loginProcessingUrl: String): RequestMatcher {
		return AntPathRequestMatcher(loginProcessingUrl, "POST")
	}

	override fun configure(http: H) {
		if (authenticationManager == null) {
			authenticationManager = http.getSharedObject(AuthenticationManager::class.java)
		}
		authenticationFilter.setAuthenticationManager(authenticationManager)
		authenticationFilter.setAuthenticationSuccessHandler(successHandler)
		authenticationFilter.setAuthenticationFailureHandler(failureHandler)

		http.getSharedObject(SessionAuthenticationStrategy::class.java)?.let {
			authenticationFilter.setSessionAuthenticationStrategy(it)
		}
		http.setSharedObject(AjaxLoginProcessingFilter::class.java, authenticationFilter)
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
	}

	fun successHandlerAjax(successHandler: AuthenticationSuccessHandler): AjaxLoginConfigurer<H> {
		this.successHandler = successHandler
		return this
	}

	fun failureHandlerAjax(authenticationFailureHandler: AuthenticationFailureHandler): AjaxLoginConfigurer<H> {
		this.failureHandler = authenticationFailureHandler
		return this
	}

	fun setAuthenticationManager(authenticationManager: AuthenticationManager): AjaxLoginConfigurer<H> {
		this.authenticationManager = authenticationManager
		return this
	}

	public override fun loginPage(loginPage: String): AjaxLoginConfigurer<H> {
		return super.loginPage(loginPage)
	}
}

