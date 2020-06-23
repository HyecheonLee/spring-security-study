package com.hyecheon.springsecuritystudy.domain.entity

import com.hyecheon.springsecuritystudy.domain.dto.RoleDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RoleMapper {
	fun toEntity(roleDto: RoleDto): Role
	fun toRoleDto(role: Role): RoleDto
}