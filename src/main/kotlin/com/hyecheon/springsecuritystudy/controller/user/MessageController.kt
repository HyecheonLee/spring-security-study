package com.hyecheon.springsecuritystudy.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MessageController {
	@GetMapping("/messages")
	fun message() = let { "user/message" }

	@GetMapping("/api/messages")
	fun apiMessage() = let { "message ok" }
}