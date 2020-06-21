package com.hyecheon.springsecuritystudy.domain.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Account(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		var id: Long? = null,
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		var role: String = ""
) : Serializable

