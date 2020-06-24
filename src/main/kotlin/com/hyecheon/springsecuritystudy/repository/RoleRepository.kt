package com.hyecheon.springsecuritystudy.repository

import com.hyecheon.springsecuritystudy.domain.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoleRepository : JpaRepository<Role, Long> {

	fun findByRoleName(name: String): Role?

	@Query(value = "select r from Role r where r.roleName in(:names)")
	fun findAllByRoleName(names: List<String>): List<Role>
}