package com.hyecheon.springsecuritystudy.repository

import com.hyecheon.springsecuritystudy.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<Account, Long> {
	@Query("select a from Account a join fetch a.userRoles where a.username=:username")
	fun findByUsername(username: String): Account?

}