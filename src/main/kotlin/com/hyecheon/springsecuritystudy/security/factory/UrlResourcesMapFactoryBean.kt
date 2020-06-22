package com.hyecheon.springsecuritystudy.security.factory

import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.beans.factory.FactoryBean
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.util.matcher.RequestMatcher

class UrlResourcesMapFactoryBean(
		private val securityResourceService: SecurityResourceService
) : FactoryBean<LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>>> {

	private var resourceMap: LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>> = linkedMapOf()

	override fun getObject(): LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>> {
		if (resourceMap.isEmpty()) {
			init()
		}
		return resourceMap
	}

	private fun init() {
		resourceMap = securityResourceService.getResourceList()
	}


	override fun getObjectType(): Class<*> {
		return LinkedHashMap::class.java
	}

	override fun isSingleton(): Boolean {
		return true
	}
}