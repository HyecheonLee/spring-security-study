package com.hyecheon.springsecuritystudy.domain.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "RESOURCES")
@EntityListeners(value = [AuditingEntityListener::class])
class Resources(
		@Id
		@GeneratedValue
		@Column(name = "resource_id")
		val id: Long? = null,
		@Column(name = "resource_name")
		val resourceName: String? = null,
		@Column(name = "http_method")
		val httpMethod: String? = null,
		@Column(name = "order_num")
		val orderNum: Int = 0,
		@Column(name = "resource_type")
		val resourceType: String? = null,
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "role_resources", joinColumns = [JoinColumn(name = "resource_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
		val roleSet: Set<Role> = mutableSetOf()
) : Serializable {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Resources) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}

	override fun toString(): String {
		return "Resources(id=$id, resourceName=$resourceName, httpMethod=$httpMethod, orderNum=$orderNum, resourceType=$resourceType)"
	}
}