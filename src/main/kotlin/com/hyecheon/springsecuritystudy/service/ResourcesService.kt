package com.hyecheon.springsecuritystudy.service

import com.hyecheon.springsecuritystudy.domain.entity.Resources

interface ResourcesService {
	fun getResources(id: Long): Resources
	fun getResources(): List<Resources>
	fun createResources(resources: Resources)
	fun deleteResources(id: Long)
}