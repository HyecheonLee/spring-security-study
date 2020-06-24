package com.hyecheon.springsecuritystudy.security.filter

import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import java.io.IOException
import javax.servlet.ServletException

class PermitAllFilter(vararg permitAllResources: String) : FilterSecurityInterceptor() {
	companion object {
		const val FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied"
	}

	private var observeOncePerRequest: Boolean? = true
	private var permitAllRequestMatchers = mutableListOf<RequestMatcher>()

	init {
		permitAllResources.forEach { s ->
			permitAllRequestMatchers.add(AntPathRequestMatcher(s))
		}
	}


	override fun beforeInvocation(`object`: Any?): InterceptorStatusToken? {
		if (`object` is FilterInvocation) {
			val request = `object`.request
			if (permitAllRequestMatchers.any { requestMatcher -> requestMatcher.matches(request) }) {
				return null
			}
		}
		return super.beforeInvocation(`object`)
	}

	@Throws(IOException::class, ServletException::class)
	override fun invoke(fi: FilterInvocation) {
		if (fi.request != null
				&& fi.request.getAttribute(FILTER_APPLIED) != null
				&& observeOncePerRequest!!) {
			// filter already applied to this request and user wants us to observe
			// once-per-request handling, so don't re-do security checking
			fi.chain.doFilter(fi.request, fi.response)
		} else {
			// first time this request being called, so perform security checking
			if (fi.request != null && observeOncePerRequest!!) {
				fi.request.setAttribute(FILTER_APPLIED, java.lang.Boolean.TRUE)
			}
			val token = super.beforeInvocation(fi)
			try {
				fi.chain.doFilter(fi.request, fi.response)
			} finally {
				super.finallyInvocation(token)
			}
			super.afterInvocation(token, null)
		}
	}
}