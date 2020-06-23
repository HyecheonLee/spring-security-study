package com.hyecheon.springsecuritystudy.domain.entity

import com.hyecheon.springsecuritystudy.domain.dto.ResourcesDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ResourcesMapper {
	fun toEntity(resourcesDto: ResourcesDto): Resources

}