package com.hyecheon.springsecuritystudy.security.config

import com.hyecheon.springsecuritystudy.security.filter.AjaxLoginProcessingFilter
import com.hyecheon.springsecuritystudy.security.provider.AjaxAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@Order(0)
class AjaxSecurityConfig(
		val userDetailService: UserDetailsService,
		val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(ajaxAuthenticationProvider())
	}

	override fun configure(http: HttpSecurity) {
		http
				.antMatcher("/api/**")
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
		http.csrf().disable()
	}

	@Bean
	fun ajaxAuthenticationProvider() =
			AjaxAuthenticationProvider(userDetailService, passwordEncoder)

	@Bean
	fun ajaxLoginProcessingFilter(): AjaxLoginProcessingFilter {
		val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
		ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean())
		return ajaxLoginProcessingFilter
	}
}