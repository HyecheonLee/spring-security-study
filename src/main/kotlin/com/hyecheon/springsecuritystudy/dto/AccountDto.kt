package com.hyecheon.springsecuritystudy.dto

import com.hyecheon.springsecuritystudy.domain.Account
import org.mapstruct.Mapper

data class AccountDto(
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var role: String = ""
)

@Mapper(componentModel = "spring")
interface AccountDtoMapper {
	fun toEntity(dto: AccountDto): Account {
		TODO("Not yet implemented")
	}

	fun toDto(entity: Account): AccountDto {
		TODO("Not yet implemented")
	}
}
