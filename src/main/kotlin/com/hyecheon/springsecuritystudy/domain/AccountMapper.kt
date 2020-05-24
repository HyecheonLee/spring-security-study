package com.hyecheon.springsecuritystudy.domain

import com.hyecheon.springsecuritystudy.dto.AccountLoginDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AccountMapper {
	fun toEntity(loginDto: AccountLoginDto): Account

	fun toAccountDto(entity: Account): AccountLoginDto
}