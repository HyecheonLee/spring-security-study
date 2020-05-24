package com.hyecheon.springsecuritystudy.security.service

import com.hyecheon.springsecuritystudy.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailService")
class CustomUserDetailService(
		private val userRepository: UserRepository
) : UserDetailsService {
	override fun loadUserByUsername(username: String): UserDetails {
		val account = userRepository.findByUsername(username)
				?: throw UsernameNotFoundException("UsernameNotFoundException : $username")

		val roles = listOf<GrantedAuthority>(SimpleGrantedAuthority(account.role))

		return AccountContext(account, roles)
	}
}

