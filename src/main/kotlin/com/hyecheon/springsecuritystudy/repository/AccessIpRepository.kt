package com.hyecheon.springsecuritystudy.repository

import com.hyecheon.springsecuritystudy.domain.entity.AccessIp
import org.springframework.data.jpa.repository.JpaRepository

interface AccessIpRepository : JpaRepository<AccessIp, Long> {
	fun findByIpAddress(ipAddress: String): AccessIp?
}