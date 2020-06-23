package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.entity.Role
import com.hyecheon.springsecuritystudy.repository.RoleRepository
import com.hyecheon.springsecuritystudy.service.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class RoleServiceImpl(
		private val roleRepository: RoleRepository) : RoleService {

	override fun getRole(id: Long): Role {
		return roleRepository.findById(id).orElse(Role())
	}

	override fun getRoles(): List<Role> {
		return roleRepository.findAll()
	}

	override fun createRole(role: Role) {
		val save = roleRepository.save(role)

	}

	override fun deleteRole(id: Long) {
		roleRepository.deleteById(id)
	}

	override fun findByRoleName(name: String): Role {
		return roleRepository.findByRoleName(name = name) ?: Role()
	}
}