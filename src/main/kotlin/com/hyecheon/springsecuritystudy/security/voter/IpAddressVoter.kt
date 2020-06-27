package com.hyecheon.springsecuritystudy.security.voter

import com.hyecheon.springsecuritystudy.security.service.SecurityResourceService
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.AccessDecisionVoter.ACCESS_ABSTAIN
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.WebAuthenticationDetails

class IpAddressVoter(
		private val securityResourceService: SecurityResourceService
) : AccessDecisionVoter<Any> {


	override fun supports(attribute: ConfigAttribute): Boolean {
		return true
	}

	override fun supports(clazz: Class<*>): Boolean {
		return true
	}

	override fun vote(authentication: Authentication, `object`: Any, attributes: MutableCollection<ConfigAttribute>): Int {
		val details = authentication.details as WebAuthenticationDetails
		val remoteAddress = details.remoteAddress
		val accessIpList = securityResourceService.getAccessIpList()
		if (accessIpList.any { it == remoteAddress }) {
			return ACCESS_ABSTAIN
		} else {
			throw AccessDeniedException("Invalid IpAdress")
		}
	}
}