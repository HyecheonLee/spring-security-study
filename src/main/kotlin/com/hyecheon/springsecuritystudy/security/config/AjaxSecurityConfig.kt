package com.hyecheon.springsecuritystudy.security.config

import com.hyecheon.springsecuritystudy.security.common.AjaxLoginAuthenticationEntryPoint
import com.hyecheon.springsecuritystudy.security.filter.AjaxLoginProcessingFilter
import com.hyecheon.springsecuritystudy.security.handler.AjaxAccessDeniedHandler
import com.hyecheon.springsecuritystudy.security.handler.AjaxAuthenticationFailureHandler
import com.hyecheon.springsecuritystudy.security.handler.AjaxAuthenticationSuccessHandler
import com.hyecheon.springsecuritystudy.security.provider.AjaxAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@Order(0)
class AjaxSecurityConfig : WebSecurityConfigurerAdapter() {
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(ajaxAuthenticationProvider())
	}

	override fun configure(http: HttpSecurity) {
		http
				.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers("/api/message").hasRole("MANAGER")
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)

		http
				.exceptionHandling()
				.authenticationEntryPoint(AjaxLoginAuthenticationEntryPoint()) //인증 X
				.accessDeniedHandler(ajaxAccessDeniedHandler()) // 인증 O  -> 권한  X
		http.csrf().disable()
	}

	@Bean
	fun ajaxAccessDeniedHandler() = AjaxAccessDeniedHandler()

	@Bean
	fun ajaxAuthenticationSuccessHandler() = AjaxAuthenticationSuccessHandler()

	@Bean
	fun ajaxAuthenticationFailureHandler() = AjaxAuthenticationFailureHandler()

	@Bean
	fun ajaxAuthenticationProvider(): AuthenticationProvider = AjaxAuthenticationProvider()

	@Bean
	fun ajaxLoginProcessingFilter(): AjaxLoginProcessingFilter {
		val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
		ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean())
		ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler())
		ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler())
		return ajaxLoginProcessingFilter
	}
}