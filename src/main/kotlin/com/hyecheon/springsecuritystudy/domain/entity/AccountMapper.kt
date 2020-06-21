package com.hyecheon.springsecuritystudy.domain.entity

import com.hyecheon.springsecuritystudy.domain.dto.AccountLoginDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AccountMapper {

	fun toEntity(loginDto: AccountLoginDto): Account

	fun toAccountDto(entity: Account): AccountLoginDto
}