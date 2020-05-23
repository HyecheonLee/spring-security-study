package com.hyecheon.springsecuritystudy.service

import com.hyecheon.springsecuritystudy.domain.Account

interface UserService {

	fun createUser(account: Account)
}