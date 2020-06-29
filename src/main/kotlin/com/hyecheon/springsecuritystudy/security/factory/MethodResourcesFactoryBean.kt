package com.hyecheon.springsecuritystudy.security.factory

import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.beans.factory.FactoryBean
import org.springframework.security.access.ConfigAttribute

class MethodResourcesFactoryBean(private val securityResourceService: SecurityResourceService)
	: FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

	private lateinit var resourceMap: LinkedHashMap<String, List<ConfigAttribute>>
	private fun init() {
		resourceMap = securityResourceService.getMethodResourceList()
	}

	override fun getObject(): LinkedHashMap<String, List<ConfigAttribute>> {
		if (!::resourceMap.isInitialized) {
			init()
		}
		return resourceMap
	}

	override fun getObjectType(): Class<*> {
		return LinkedHashMap::class.java
	}
}