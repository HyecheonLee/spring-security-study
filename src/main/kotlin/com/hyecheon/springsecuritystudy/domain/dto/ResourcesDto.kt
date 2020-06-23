package com.hyecheon.springsecuritystudy.domain.dto

import com.hyecheon.springsecuritystudy.domain.entity.Resources
import com.hyecheon.springsecuritystudy.domain.entity.Role

data class ResourcesDto(
		var id: Long? = null,
		var resourceName: String = "",
		var httpMethod: String = "",
		var orderNum: Int = 0,
		var resourceType: String = "",
		var roleName: String = "",
		var roleSet: MutableSet<Role> = mutableSetOf()) {
	companion object {
		fun fromEntity(resources: Resources): ResourcesDto {
			return ResourcesDto(
					id = resources.id,
					resourceName = resources.resourceName ?: "",
					resourceType = resources.resourceType ?: "",
					httpMethod = resources.httpMethod ?: "",
					roleSet = resources.roleSet
			)
		}
	}
}