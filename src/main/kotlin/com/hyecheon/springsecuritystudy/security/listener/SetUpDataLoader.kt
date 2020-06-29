package com.hyecheon.springsecuritystudy.security.listener

import com.hyecheon.springsecuritystudy.domain.entity.*
import com.hyecheon.springsecuritystudy.repository.*
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.atomic.AtomicInteger


@Component
@Transactional
class SetUpDataLoader(
		private val roleHierarchyRepository: RoleHierarchyRepository,
		private val resourcesRepository: ResourcesRepository,
		private val userRepository: UserRepository,
		private val passwordEncoder: PasswordEncoder,
		private val roleRepository: RoleRepository,
		private val accessIpRepository: AccessIpRepository) : ApplicationListener<ContextRefreshedEvent> {

	private var alreadySetup = false

	private val count = AtomicInteger(0)

	override fun onApplicationEvent(event: ContextRefreshedEvent) {
		if (alreadySetup) {
			return
		}
		setupSecurityResources()
		setupAccessIpData()
		alreadySetup = true
	}

	private fun setupSecurityResources() {
		val roles: MutableSet<Role> = mutableSetOf()
		val adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자")
		roles.add(adminRole)
		createResourceIfNotFound("/admin/**", "", roles, "url")
//		createResourceIfNotFound("execution(public * io.security.corespringsecurity.aopsecurity.*Service.pointcut*(..))", "", roles, "pointcut")
		createUserIfNotFound("admin", "admin@admin.com", "pass", roles)
		val managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저권한")
		val userRole = createRoleIfNotFound("ROLE_USER", "사용자권한")
		createRoleHierarchyIfNotFound(managerRole, adminRole)
		createRoleHierarchyIfNotFound(userRole, managerRole)
	}

	@Transactional
	fun createRoleIfNotFound(roleName: String, roleDesc: String): Role {
		var role = roleRepository.findByRoleName(roleName)
		if (role == null) {
			role = Role.builder()
					.roleName(roleName)
					.roleDesc(roleDesc)
					.build()
		}
		return roleRepository.save(role!!)
	}

	@Transactional
	fun createUserIfNotFound(userName: String, email: String, password: String, roleSet: MutableSet<Role>): Account {
		var account = userRepository.findByUsername(userName)
		if (account == null) {
			account = Account.builder()
					.username(userName)
					.email(email)
					.password(passwordEncoder.encode(password))
					.userRoles(roleSet)
					.build()
		}
		return userRepository.save(account!!)
	}

	@Transactional
	fun createResourceIfNotFound(resourceName: String, httpMethod: String, roleSet: MutableSet<Role>, resourceType: String): Resources {
		var resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod)
		if (resources == null) {
			resources = Resources.builder()
					.resourceName(resourceName)
					.roleSet(roleSet)
					.httpMethod(httpMethod)
					.resourceType(resourceType)
					.orderNum(count.incrementAndGet())
					.build()
		}
		return resourcesRepository.save(resources!!)
	}

	@Transactional
	fun createRoleHierarchyIfNotFound(childRole: Role, parentRole: Role) {
		var roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.roleName ?: "")
		if (roleHierarchy == null) {
			roleHierarchy = RoleHierarchy.builder()
					.childName(parentRole.roleName)
					.build()
		}
		val parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy!!)
		roleHierarchy = roleHierarchyRepository.findByChildName(childRole.roleName ?: "")
		if (roleHierarchy == null) {
			roleHierarchy = RoleHierarchy.builder()
					.childName(childRole.roleName)
					.build()
		}
		val childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy!!)
		childRoleHierarchy.parentName = parentRoleHierarchy
		roleHierarchyRepository.save(childRoleHierarchy)
	}

	private fun setupAccessIpData() {
		val byIpAddress = accessIpRepository.findByIpAddress("0:0:0:0:0:0:0:1")
		if (byIpAddress == null) {
			val accessIp = AccessIp.builder()
					.ipAddress("0:0:0:0:0:0:0:1")
					.build()
			accessIpRepository.save(accessIp)
		}
	}
}