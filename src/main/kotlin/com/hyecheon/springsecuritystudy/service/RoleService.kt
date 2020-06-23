package com.hyecheon.springsecuritystudy.service

import com.hyecheon.springsecuritystudy.domain.entity.Role

interface RoleService {
	fun getRole(id: Long): Role
	fun getRoles(): List<Role>
	fun createRole(role: Role)
	fun deleteRole(id: Long)
	fun findByRoleName(name: String): Role
}