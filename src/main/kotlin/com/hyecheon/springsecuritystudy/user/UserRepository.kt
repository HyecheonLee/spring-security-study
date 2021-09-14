package com.hyecheon.springsecuritystudy.user

import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}