package com.hyecheon.springsecuritystudy.controller.admin

import com.hyecheon.springsecuritystudy.domain.dto.ResourcesDto
import com.hyecheon.springsecuritystudy.domain.entity.Resources
import com.hyecheon.springsecuritystudy.domain.entity.ResourcesMapper
import com.hyecheon.springsecuritystudy.domain.entity.Role
import com.hyecheon.springsecuritystudy.security.meatadatasource.UrlFilterInvocationSecurityMetadataSource
import com.hyecheon.springsecuritystudy.service.ResourcesService
import com.hyecheon.springsecuritystudy.service.RoleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping


@Controller
class ResourcesController(
		val resourcesService: ResourcesService,
		val resourcesMapper: ResourcesMapper,
		val filterInvocationSecurityMetadataSource: UrlFilterInvocationSecurityMetadataSource,
		val roleService: RoleService) {


	@GetMapping(value = ["/admin/resources"])
	fun getResources(model: Model): String {
		val resources = resourcesService.getResources()
		model.addAttribute("resources", resources)
		return "admin/resource/list"
	}

	@PostMapping(value = ["/admin/resources"])
	fun createResources(resourcesDto: ResourcesDto) = let {
		val role = roleService.findByRoleName(resourcesDto.roleName)
		val resources = resourcesMapper.toEntity(resourcesDto)
		resources.addRole(role)
		resourcesService.createResources(resources)
		filterInvocationSecurityMetadataSource.reload()
		"redirect:/admin/resources"
	}

	@GetMapping(value = ["/admin/resources/register"])
	fun viewRoles(model: Model): String {
		val roleList = roleService.getRoles()
		model.addAttribute("roleList", roleList)

		val resources = ResourcesDto()
		model.addAttribute("resources", resources)

		return "admin/resource/detail"
	}

	@GetMapping(value = ["/admin/resources/{id}"])
	fun getResources(@PathVariable id: Long, model: Model): String? {
		val roleList: List<Role> = roleService.getRoles()
		model.addAttribute("roleList", roleList)
		val resources: Resources = resourcesService.getResources(id)

		val resourcesDto: ResourcesDto = ResourcesDto.fromEntity(resources)
		model.addAttribute("resources", resourcesDto)
		return "admin/resource/detail"
	}
}