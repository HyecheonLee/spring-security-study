package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import com.hyecheon.springsecuritystudy.domain.entity.Account
import com.hyecheon.springsecuritystudy.repository.RoleRepository
import com.hyecheon.springsecuritystudy.repository.UserRepository
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserServiceImpl(
		private val userRepository: UserRepository,
		private val roleRepository: RoleRepository,
		val passwordEncoder: PasswordEncoder) : UserService {

	override fun createUser(account: Account) {
		val role = roleRepository.findByRoleName("ROLE_USER")
		role?.let {
			account.addRole(it)
		}
		userRepository.save(account)

	}

	override fun getUsers(): List<Account> {
		return userRepository.findAll()
	}

	override fun modifyUser(accountDto: AccountDto) {
		val id = accountDto.id ?: throw IllegalArgumentException("id는 필수입니다.")
		val account = userRepository.findById(id).orElseThrow { IllegalArgumentException("잘못된 id[$id] 입니다") }
		accountDto.password = passwordEncoder.encode(accountDto.password)

		account.update(accountDto)

		if (accountDto.roles.isNotEmpty()) {
			val roles = roleRepository.findAllByRoleName(accountDto.roles)
			account.userRoles = roles.toMutableSet()
		}

	}

	override fun getUser(id: Long): Account {
		return userRepository.findById(id).orElseThrow { IllegalArgumentException("잘못된 id[$id] 입니다") }
	}

	override fun deleteUser(id: Long) {
		userRepository.deleteById(id)
	}
}