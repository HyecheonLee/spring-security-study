package com.hyecheon.springsecuritystudy.repository

import com.hyecheon.springsecuritystudy.domain.entity.RoleHierarchy
import org.springframework.data.jpa.repository.JpaRepository

interface RoleHierarchyRepository : JpaRepository<RoleHierarchy, Long> {
	fun findByChildName(roleName: String): RoleHierarchy?
}