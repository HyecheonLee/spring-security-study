package com.hyecheon.springsecuritystudy.security.service

import com.hyecheon.springsecuritystudy.domain.entity.Resources
import com.hyecheon.springsecuritystudy.repository.ResourcesRepository
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Service

@Service
class SecurityResourceService(private val resourcesRepository: ResourcesRepository) {
	fun getResourceList(): LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>> {
		val resourcesList = resourcesRepository.findAllResources()
		return resourcesList.fold(linkedMapOf(), { acc: LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>>, resources: Resources ->
			acc[AntPathRequestMatcher(resources.resourceName)] =
					resources.roleSet.fold(mutableListOf(), { roles: MutableList<ConfigAttribute>, role ->
						roles.add(SecurityConfig(role.roleName))
						roles
					})
			acc
		})
	}
}