package com.hyecheon.springsecuritystudy.repository

import com.hyecheon.springsecuritystudy.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {

}