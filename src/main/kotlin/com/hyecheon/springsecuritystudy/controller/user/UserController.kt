package com.hyecheon.springsecuritystudy.controller.user

import com.hyecheon.springsecuritystudy.dto.AccountLoginDto
import com.hyecheon.springsecuritystudy.domain.AccountMapper
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
		val passwordEncoder: PasswordEncoder,
		val userService: UserService,
		val accountMapper: AccountMapper
) {

	@GetMapping("/mypage")
	fun myPage() = "user/mypage"

	@GetMapping("/users")
	fun createUser() = "user/login/register"

	@PostMapping("/users")
	fun createUser(accountLoginDto: AccountLoginDto) =
			let {
				accountLoginDto.password = passwordEncoder.encode(accountLoginDto.password)
				val account = accountMapper.toEntity(accountLoginDto)
				userService.createUser(account)
				"redirect:/"
			}
}