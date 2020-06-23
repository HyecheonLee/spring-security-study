package com.hyecheon.springsecuritystudy.controller.admin

import com.hyecheon.springsecuritystudy.domain.dto.RoleDto
import com.hyecheon.springsecuritystudy.domain.entity.Role
import com.hyecheon.springsecuritystudy.domain.entity.RoleMapper
import com.hyecheon.springsecuritystudy.service.RoleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping


@Controller
class RoleController(
		private val roleService: RoleService,
		private val roleMapper: RoleMapper) {
	@GetMapping(value = ["/admin/roles"])
	fun getRoles(model: Model): String? {
		val roles: List<Role> = roleService.getRoles()
		model.addAttribute("roles", roles)
		return "admin/role/list"
	}

	@GetMapping(value = ["/admin/roles/register"])
	fun viewRoles(model: Model): String? {
		val role = RoleDto()
		model.addAttribute("role", role)
		return "admin/role/detail"
	}

	@PostMapping(value = ["/admin/roles"])
	fun createRole(roleDto: RoleDto): String {
		val role: Role = roleMapper.toEntity(roleDto)
		roleService.createRole(role)
		return "redirect:/admin/roles"
	}

	@GetMapping(value = ["/admin/roles/{id}"])
	fun getRole(@PathVariable id: String?, model: Model): String? {
		val role: Role = roleService.getRole(java.lang.Long.valueOf(id))
		val roleDto: RoleDto = RoleDto.fromEntity(role)
		model.addAttribute("role", roleDto)
		return "admin/role/detail"
	}

	@GetMapping(value = ["/admin/roles/delete/{id}"])
	fun removeResources(@PathVariable id: Long, model: Model): String {
		roleService.deleteRole(id)
		return "redirect:/admin/resources"
	}
}