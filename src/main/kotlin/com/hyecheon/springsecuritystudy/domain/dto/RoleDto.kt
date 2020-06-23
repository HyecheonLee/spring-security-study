package com.hyecheon.springsecuritystudy.domain.dto

import com.hyecheon.springsecuritystudy.domain.entity.Role

data class RoleDto(var id: Long? = null, var roleName: String = "", var roleDesc: String = "") {
	companion object {
		fun fromEntity(role: Role): RoleDto {
			return RoleDto(id = role.id, roleDesc = role.roleDesc ?: "", roleName = role.roleName ?: "")
		}
	}
}

