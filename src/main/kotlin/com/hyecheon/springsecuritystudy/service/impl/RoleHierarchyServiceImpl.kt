package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.repository.RoleHierarchyRepository
import com.hyecheon.springsecuritystudy.service.RoleHierarchyService
import org.springframework.stereotype.Service

@Service
class RoleHierarchyServiceImpl(
		private val roleHierarchyRepository: RoleHierarchyRepository) : RoleHierarchyService {
	override fun findAllHierarchy(): String {
		val rolesHierarchy = roleHierarchyRepository.findAll()

		return rolesHierarchy.filter { roleHierarchy -> roleHierarchy.parentName != null }
				.joinToString("\n") { roleHierarchy ->
					"${roleHierarchy.parentName!!.childName} > ${roleHierarchy.childName}"
				} + "\n"
	}
}