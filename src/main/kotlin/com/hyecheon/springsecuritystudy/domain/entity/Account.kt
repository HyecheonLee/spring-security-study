package com.hyecheon.springsecuritystudy.domain.entity

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import java.io.Serializable
import javax.persistence.*


@Entity
data class Account(
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		var id: Long? = null,
		var username: String = "",
		var password: String = "",
		var email: String = "",
		var age: String = "",
		@ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
		@JoinTable(name = "account_roles", joinColumns = [JoinColumn(name = "account_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
		var userRoles: MutableSet<Role> = mutableSetOf()
) : Serializable {
	fun addRole(role: Role) {
		userRoles.add(role)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Account) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}

	override fun toString(): String {
		return "Account(id=$id, username='$username', password='$password', email='$email', age='$age')"
	}

	fun update(accountDto: AccountDto) {
		if (accountDto.age.isNotEmpty()) {
			age = accountDto.age
		}
		if (accountDto.email.isNotEmpty()) {
			email = accountDto.email
		}
		if (accountDto.username.isNotEmpty()) {
			username = accountDto.username
		}
		if (accountDto.password.isNotEmpty()) {
			password = accountDto.password
		}
	}
}
