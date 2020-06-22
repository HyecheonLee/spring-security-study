package com.hyecheon.springsecuritystudy.config

import com.hyecheon.springsecuritystudy.repository.ResourcesRepository
import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

	//	@Bean
	fun securityResourceService(resourcesRepository: ResourcesRepository): SecurityResourceService {
		return SecurityResourceService(resourcesRepository)
	}
}