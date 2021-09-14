package com.hyecheon.springsecuritystudy.user

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {


    fun signup(username: String, password: String) = run {
        if (userRepository.findByUsername(username) != null) {
            throw AlreadyRegisteredUserException()
        }
        userRepository.save(User(username = username,
            password = passwordEncoder.encode(password),
            authority = "ROLE_USER"))
    }

    fun signupAdmin(username: String, password: String) = run {
        if (userRepository.findByUsername(username) != null) {
            throw AlreadyRegisteredUserException()
        }
        userRepository.save(User(username = username,
            password = passwordEncoder.encode(password),
            authority = "ROLE_ADMIN"))
    }

    fun findByUsername(username: String) = run {
        userRepository.findByUsername(username) ?: throw UserNotFoundException(username)
    }
}