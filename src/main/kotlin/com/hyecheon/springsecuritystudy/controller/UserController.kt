package com.hyecheon.springsecuritystudy.controller

import com.hyecheon.springsecuritystudy.dto.AccountDto
import com.hyecheon.springsecuritystudy.dto.AccountDtoMapper
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
		val passwordEncoder: PasswordEncoder,
		val userService: UserService,
		val accountDtoMapper: AccountDtoMapper
) {

	@GetMapping("/mypage")
	fun myPage() = "user/mypage"

	@GetMapping("/users")
	fun createUser() = "user/login/register"

	@PostMapping("/users")
	fun createUser(accountDto: AccountDto) = with(accountDto) {
		this.password = passwordEncoder.encode(this.password)
		this
	}.let { it ->
		val account = accountDtoMapper.toEntity(it)
		userService.createUser(account)
		"redirect:/"
	}
}