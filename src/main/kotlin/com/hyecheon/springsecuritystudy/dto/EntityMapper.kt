package com.hyecheon.springsecuritystudy.dto

interface EntityMapper<D, E> {
	fun toEntity(dto: D): E
	fun toDto(entity: E): D
}