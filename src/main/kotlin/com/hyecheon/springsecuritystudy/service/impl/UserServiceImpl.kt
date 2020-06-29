package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import com.hyecheon.springsecuritystudy.domain.entity.Account
import com.hyecheon.springsecuritystudy.domain.entity.Role
import com.hyecheon.springsecuritystudy.repository.RoleRepository
import com.hyecheon.springsecuritystudy.repository.UserRepository
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.security.access.annotation.Secured
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
		val roles: MutableSet<Role> = mutableSetOf()
		role?.let {
			roles.add(it)
			account.userRoles = roles
		}
		userRepository.save(account)

	}

	override fun getUsers(): List<Account> {
		return userRepository.findAll()
	}

	override fun modifyUser(accountDto: AccountDto) {
		val id = accountDto.id ?: throw IllegalArgumentException("id는 필수입니다.")
		val account = userRepository.findById(id).orElseThrow { IllegalArgumentException("잘못된 id[$id] 입니다") }
		accountDto.password?.let {
			accountDto.password = passwordEncoder.encode(it)
		}
		accountDto.age?.let {
			account.age = it.toInt()
		}
		account.email?.let {
			account.email = it
		}
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

	@Secured("ROLE_MANAGER")
	override fun order() {
		println("order")
	}

}