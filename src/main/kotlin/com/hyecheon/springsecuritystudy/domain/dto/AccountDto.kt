package com.hyecheon.springsecuritystudy.domain.dto

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
		var username: String? = null,
		var password: String? = null,
		var email: String? = null,
		var age: String? = null,
		var roles: List<String> = mutableListOf()) {
}