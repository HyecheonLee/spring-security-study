package com.hyecheon.springsecuritystudy.meatadatasource

import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher

class UrlFilterInvocationSecurityMetadataSource : FilterInvocationSecurityMetadataSource {

	private val requestMap = mutableMapOf<RequestMatcher, List<ConfigAttribute>>()

	override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute> {
		return requestMap.flatMap { entry -> entry.value }.toMutableList()
	}

	override fun supports(clazz: Class<*>?): Boolean {
		return FilterInvocation::class.java.isAssignableFrom(clazz)
	}

	override fun getAttributes(`object`: Any?): MutableCollection<ConfigAttribute> {
		val request = (`object` as FilterInvocation).request

		requestMap[AntPathRequestMatcher("/mypage")] = listOf(SecurityConfig("ROLE_USER"))

		return requestMap
				.filter { entry ->
					entry.key.matches(request)
				}
				.map { entry -> entry.value }
				.firstOrNull()?.toMutableList() ?: mutableListOf()
	}
}