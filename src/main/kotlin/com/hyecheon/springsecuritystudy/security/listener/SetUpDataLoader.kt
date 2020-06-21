package com.hyecheon.springsecuritystudy.security.listener

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent

class SetUpDataLoader : ApplicationListener<ContextRefreshedEvent> {

	private val alreadySetup = false

	override fun onApplicationEvent(event: ContextRefreshedEvent) {
		TODO("Not yet implemented")
	}

}