package com.hyecheon.springsecuritystudy.service

import com.hyecheon.springsecuritystudy.domain.entity.Account

interface UserService {

	fun createUser(account: Account)
}