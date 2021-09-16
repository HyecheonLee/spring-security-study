package com.hyecheon.springsecuritystudy.user

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
internal class UserServiceTest {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository


    @DisplayName("1. signup")
    @Test
    internal fun test1() {
        val username = "user123"
        val password: String = "password"

        val user = userService.signup(username, password)

        then(user.id).isNotNull
        then(user.username).isEqualTo("user123")
        then(user.password).startsWith("{bcrypt}")
        then(user.authorities).hasSize(1)
        then(user.authorities.first().authority).isEqualTo("ROLE_USER")
        then(user.isAdmin()).isFalse
        then(user.isAccountNonExpired).isTrue
        then(user.isAccountNonLocked).isTrue
        then(user.isEnabled).isTrue
        then(user.isCredentialsNonExpired).isTrue
    }

    @DisplayName("2. signupAdmin")
    @Test
    internal fun test2() {
        val username = "admin123"
        val password: String = "password"

        val user = userService.signupAdmin(username, password)

        then(user.id).isNotNull
        then(user.username).isEqualTo("admin123")
        then(user.password).startsWith("{bcrypt}")
        then(user.authorities).hasSize(1)
        then(user.authorities.first().authority).isEqualTo("ROLE_ADMIN")
        then(user.isAdmin()).isTrue
        then(user.isAccountNonExpired).isTrue
        then(user.isAccountNonLocked).isTrue
        then(user.isEnabled).isTrue
        then(user.isCredentialsNonExpired).isTrue
    }

    @DisplayName("3. findByUsername")
    @Test
    internal fun `test3`() {

        userRepository.save(User(username = "user123", password = "password", authority = "ROLE_USER"))

        val user = userService.findByUsername("user123")

        then(user.id).isNotNull
    }

}