package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.entity.Account
import com.hyecheon.springsecuritystudy.repository.RoleRepository
import com.hyecheon.springsecuritystudy.repository.UserRepository
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserServiceImpl(
		private val userRepository: UserRepository,
		private val roleRepository: RoleRepository
) : UserService {
	override fun createUser(account: Account) {
		val role = roleRepository.findByRoleName("ROLE_USER")
		role?.let {
			account.addRole(it)
		}
		userRepository.save(account)
	}
}