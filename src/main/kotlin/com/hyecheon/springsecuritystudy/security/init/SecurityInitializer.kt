package com.hyecheon.springsecuritystudy.security.init

import com.hyecheon.springsecuritystudy.service.RoleHierarchyService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.stereotype.Component

@Component
class SecurityInitializer(
		private val roleHierarchyService: RoleHierarchyService,
		private val roleHierarchyImpl: RoleHierarchyImpl
) : ApplicationRunner {

	override fun run(args: ApplicationArguments) {
		val allHierarchy = roleHierarchyService.findAllHierarchy()
		roleHierarchyImpl.setHierarchy(allHierarchy)
	}

}