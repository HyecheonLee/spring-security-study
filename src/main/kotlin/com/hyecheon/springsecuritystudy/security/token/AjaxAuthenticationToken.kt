package com.hyecheon.springsecuritystudy.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.SpringSecurityCoreVersion

open class AjaxAuthenticationToken : AbstractAuthenticationToken {

	private val principal: Any
	private var credentials: Any?

	constructor(principal: Any, credentials: Any) : super(null) {
		this.principal = principal
		this.credentials = credentials
		isAuthenticated = false
	}

	constructor(principal: Any, credentials: Any,
	            authorities: Collection<GrantedAuthority?>?) : super(authorities) {
		this.principal = principal
		this.credentials = credentials
		super.setAuthenticated(true) // must use super, as we override
	}

	override fun getCredentials(): Any? {
		return credentials
	}

	override fun getPrincipal(): Any {
		return principal
	}

	@Throws(IllegalArgumentException::class)
	override fun setAuthenticated(isAuthenticated: Boolean) {
		require(!isAuthenticated) { "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead" }
		super.setAuthenticated(false)
	}

	override fun eraseCredentials() {
		super.eraseCredentials()
		credentials = null
	}

	companion object {
		private const val serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID
	}
}
