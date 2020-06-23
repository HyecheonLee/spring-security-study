package com.hyecheon.springsecuritystudy.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MessageController {

	@GetMapping("/messages")
	fun message(): String {
		return "user/messages"
	}

	@ResponseBody
	@GetMapping("/api/messages")
	fun apiMessage() = let { """{"message":"message ok"}""" }
}