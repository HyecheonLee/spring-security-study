package com.hyecheon.springsecuritystudy.dto

data class AccountLoginDto(
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var role: String = ""
)

data class AccountDto(
		var username: String = "",
		var password: String = ""
)