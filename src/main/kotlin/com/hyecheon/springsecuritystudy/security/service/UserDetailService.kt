package com.hyecheon.springsecuritystudy.security.service

import com.hyecheon.springsecuritystudy.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailService")
class UserDetailService(
		private val userRepository: UserRepository
) : UserDetailsService {
	override fun loadUserByUsername(username: String): UserDetails {
		val account = userRepository.findByUsername(username)
				?: throw UsernameNotFoundException("UsernameNotFoundException : $username")
		val roles = account.userRoles.map { role -> SimpleGrantedAuthority(role.roleName) }
		return AccountContext(account, roles)
	}
}

