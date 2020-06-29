package com.hyecheon.springsecuritystudy.security.config

import com.hyecheon.springsecuritystudy.security.factory.MethodResourcesFactoryBean
import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource
import org.springframework.security.access.method.MethodSecurityMetadataSource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration() {
	@Autowired
	lateinit var securityResourceService: SecurityResourceService

	override fun customMethodSecurityMetadataSource(): MethodSecurityMetadataSource {
		return mapBasedMethodSecurityMetadataSource()
	}

	@Bean
	fun mapBasedMethodSecurityMetadataSource(): MapBasedMethodSecurityMetadataSource {
		return MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().`object`)
	}

	@Bean
	fun methodResourcesMapFactoryBean(): MethodResourcesFactoryBean {
		return MethodResourcesFactoryBean(securityResourceService)
	}
}