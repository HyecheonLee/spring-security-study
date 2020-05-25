package com.hyecheon.springsecuritystudy.domain

import com.hyecheon.springsecuritystudy.dto.AccountLoginDto
import org.mapstruct.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@Mapper(componentModel = "spring")
interface AccountMapper {

	fun toEntity(loginDto: AccountLoginDto): Account

	fun toAccountDto(entity: Account): AccountLoginDto
}