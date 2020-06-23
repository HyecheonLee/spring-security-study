package com.hyecheon.springsecuritystudy.security.meatadatasource

import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.context.annotation.Bean
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.util.matcher.RequestMatcher

class UrlFilterInvocationSecurityMetadataSource(
		private val requestMap: LinkedHashMap<RequestMatcher, MutableList<ConfigAttribute>>,
		private val securityResourceService: SecurityResourceService) : FilterInvocationSecurityMetadataSource {
	override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute> {
		return requestMap.flatMap { entry -> entry.value }.toMutableList()
	}

	override fun supports(clazz: Class<*>?): Boolean {
		return FilterInvocation::class.java.isAssignableFrom(clazz)
	}

	override fun getAttributes(`object`: Any?): MutableCollection<ConfigAttribute> {
		val request = (`object` as FilterInvocation).request
		return requestMap
				.filter { entry ->
					entry.key.matches(request)
				}
				.map { entry -> entry.value }
				.firstOrNull()?.toMutableList() ?: mutableListOf()
	}

	fun reload() {
		val reloadedMap = securityResourceService.getResourceList()
		val entries = reloadedMap.entries
		requestMap.clear()
		entries.forEach { t: MutableMap.MutableEntry<RequestMatcher, MutableList<ConfigAttribute>> ->
			requestMap[t.key] = t.value
		}

	}
}