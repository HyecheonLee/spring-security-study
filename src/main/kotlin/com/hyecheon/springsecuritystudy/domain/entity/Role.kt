package com.hyecheon.springsecuritystudy.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "ROLE")
data class Role(
		@Id
		@GeneratedValue
		@Column(name = "role_id")
		var id: Long? = null,
		@Column(name = "role_name")
		var roleName: String? = null,
		@Column(name = "role_desc")
		var roleDesc: String? = null,
		@JsonIgnore
		@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
		@OrderBy("ordernum desc")
		var resourcesSet: MutableSet<Resources> = mutableSetOf()
) : Serializable {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Role) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}

	override fun toString(): String {
		return "Role(id=$id, roleName=$roleName, roleDesc=$roleDesc)"
	}

	fun addRoleSet(resources: Resources) {
		resourcesSet.add(resources)
	}
}