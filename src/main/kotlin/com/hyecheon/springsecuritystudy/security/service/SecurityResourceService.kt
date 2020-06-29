package com.hyecheon.springsecuritystudy.security.service

import com.hyecheon.springsecuritystudy.repository.AccessIpRepository
import com.hyecheon.springsecuritystudy.repository.ResourcesRepository
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Service

@Service
class SecurityResourceService(
		private val resourcesRepository: ResourcesRepository,
		private val accessIpRepository: AccessIpRepository) {
	fun getResourceList(): LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>> {
		val resourcesList = resourcesRepository.findAllResources()
		val result: LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>> = linkedMapOf()
		resourcesList.forEach {
			val configAttributeList = it.roleSet.map { role ->
				SecurityConfig(role.roleName)
			}
			result.putIfAbsent(AntPathRequestMatcher(it.resourceName), configAttributeList.toMutableList())
		}
		return result
	}

	fun getMethodResourceList(): LinkedHashMap<String, List<ConfigAttribute>> {
		val result = linkedMapOf<String, List<ConfigAttribute>>()
		val resourcesList = resourcesRepository.findAllMethodResources()
		resourcesList.forEach {
			val configAttributeList: List<ConfigAttribute> = it.roleSet.map { role -> SecurityConfig(role.roleName) }
			result.putIfAbsent(it.resourceName, configAttributeList)
		}
		return result
	}

	fun getAccessIpList(): List<String> {
		return accessIpRepository.findAll().map { it.ipAddress }
	}
}