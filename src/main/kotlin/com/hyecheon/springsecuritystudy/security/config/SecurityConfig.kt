package com.hyecheon.springsecuritystudy.security.config

import com.hyecheon.springsecuritystudy.security.meatadatasource.UrlFilterInvocationSecurityMetadataSource
import com.hyecheon.springsecuritystudy.security.factory.UrlResourcesMapFactoryBean
import com.hyecheon.springsecuritystudy.security.handler.CustomAccessDeniedHandler
import com.hyecheon.springsecuritystudy.security.handler.CustomAuthenticationFailureHandler
import com.hyecheon.springsecuritystudy.security.handler.CustomAuthenticationSuccessHandler
import com.hyecheon.springsecuritystudy.security.provider.CustomAuthenticationProvider
import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.authentication.WebAuthenticationDetails
import javax.servlet.http.HttpServletRequest


@Configuration
@EnableWebSecurity
@Order(1)
class SecurityConfig(
		val userDetailService: UserDetailsService,
		val authenticationDetailsSource: AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>,
		val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
		val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
		val securityResourceService: SecurityResourceService) : WebSecurityConfigurerAdapter() {

	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(authenticationProvider())
	}

	@Bean
	fun authenticationProvider(): AuthenticationProvider {
		return CustomAuthenticationProvider(userDetailService, passwordEncoder())
	}


	@Bean
	fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

	@Throws(Exception::class)
	override fun configure(web: WebSecurity) {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
	}

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http
				.authorizeRequests()
				.antMatchers("/", "/users", "user/login/**", "/login*").permitAll()
				/*.antMatchers("/mypage").hasRole("USER")
				.antMatchers("/message").hasRole("MANAGER")*/
				.antMatchers("/config").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login_proc")
				.authenticationDetailsSource(authenticationDetailsSource)
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
				.permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler())
				.and()
				.addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor::class.java)

//		http.csrf().disable()
	}

	@Bean
	fun accessDeniedHandler(): CustomAccessDeniedHandler = CustomAccessDeniedHandler("/denied")

	@Bean
	fun customFilterSecurityInterceptor(): FilterSecurityInterceptor {
		val filterSecurityInterceptor = FilterSecurityInterceptor()
		filterSecurityInterceptor.securityMetadataSource = urlFilterInvocationSecurityMetadataSource()
		filterSecurityInterceptor.accessDecisionManager = affirmativeBased()
		filterSecurityInterceptor.authenticationManager = authenticationManager()
		return filterSecurityInterceptor
	}

	@Bean
	fun affirmativeBased(): AccessDecisionManager {
		return AffirmativeBased(getAccessDecisionVoters())
	}

	@Bean
	fun getAccessDecisionVoters(): MutableList<AccessDecisionVoter<*>> {
		return mutableListOf(RoleVoter())
	}

	@Bean
	fun urlFilterInvocationSecurityMetadataSource() = let {
		UrlFilterInvocationSecurityMetadataSource(urlResourceMapFactoryBean().getObject(), securityResourceService)
	}

	@Bean
	fun urlResourceMapFactoryBean(): UrlResourcesMapFactoryBean {
		return UrlResourcesMapFactoryBean(securityResourceService)
	}
}