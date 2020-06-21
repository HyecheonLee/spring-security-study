package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.entity.Account
import com.hyecheon.springsecuritystudy.repository.UserRepository
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
		val userRepository: UserRepository
) : UserService {
	override fun createUser(account: Account) {
		userRepository.save(account)
	}
}