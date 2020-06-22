package com.hyecheon.springsecuritystudy.controller.user

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MessageController {

	@GetMapping("/messages")
	fun message() = let {
		val authentication = SecurityContextHolder.getContext().authentication
		"user/messages"
	}

	@ResponseBody
	@GetMapping("/api/messages")
	fun apiMessage() = let { """{"message":"message ok"}""" }
}