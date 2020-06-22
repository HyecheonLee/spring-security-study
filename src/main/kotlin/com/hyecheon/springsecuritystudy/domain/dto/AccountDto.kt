package com.hyecheon.springsecuritystudy.domain.dto

data class AccountLoginDto(
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var roles: List<String> = mutableListOf()
)

data class AccountDto(
		var username: String = "",
		var password: String = ""
)