package com.hyecheon.springsecuritystudy.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Controller
@RequestMapping("/signup")
class SignUpController(
    private val userService: UserService,
) {
    @GetMapping
    fun signup() = run {
        "signup"
    }

    @PostMapping
    fun signup(@ModelAttribute userDto: UserRegisterDto) = run {
        userService.signup(userDto.username, userDto.password)
        "redirect:login"
    }
}