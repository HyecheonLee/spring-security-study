package com.hyecheon.springsecuritystudy.domain.dto

import com.hyecheon.springsecuritystudy.domain.entity.Account

data class AccountLoginDto(
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var roles: List<String> = mutableListOf()
)

data class AccountModifyDto(
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var roles: List<String> = mutableListOf()
)

data class AccountDto(
		var id: Long? = null,
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var roles: List<String> = mutableListOf()) {
	fun toEntity(): Account {
		return Account(id = id, username = username, password = password, email = email, age = age)
	}
}