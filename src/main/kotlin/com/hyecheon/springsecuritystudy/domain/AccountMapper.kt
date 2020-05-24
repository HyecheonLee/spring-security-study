package com.hyecheon.springsecuritystudy.domain

import com.hyecheon.springsecuritystudy.dto.AccountDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AccountMapper {
	fun toEntity(dto: AccountDto): Account

	fun toAccountDto(entity: Account): AccountDto
}