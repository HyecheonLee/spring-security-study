package com.hyecheon.springsecuritystudy.service.impl

import com.hyecheon.springsecuritystudy.domain.entity.Resources
import com.hyecheon.springsecuritystudy.repository.ResourcesRepository
import com.hyecheon.springsecuritystudy.service.ResourcesService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ResourcesServiceImpl(
		val resourcesRepository: ResourcesRepository) : ResourcesService {

	override fun getResources(id: Long): Resources {
		return resourcesRepository.findById(id).orElse(Resources())
	}

	override fun getResources(): List<Resources> {
		return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")))
	}

	override fun createResources(resources: Resources) {
		resourcesRepository.save(resources)
	}

	override fun deleteResources(id: Long) {
		resourcesRepository.deleteById(id)
	}

}