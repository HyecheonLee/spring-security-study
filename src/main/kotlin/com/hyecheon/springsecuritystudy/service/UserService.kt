package com.hyecheon.springsecuritystudy.service

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import com.hyecheon.springsecuritystudy.domain.entity.Account

interface UserService {

	fun createUser(account: Account)
	fun getUsers(): List<Account>
	fun modifyUser(accountDto: AccountDto)
	fun getUser(id: Long): Account
	fun deleteUser(id: Long)
	fun order()
}