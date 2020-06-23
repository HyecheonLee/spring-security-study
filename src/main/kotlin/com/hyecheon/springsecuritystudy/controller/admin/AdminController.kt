package com.hyecheon.springsecuritystudy.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminController {

	@GetMapping(value = ["/admin"])
	@Throws(Exception::class)
	fun home(): String? {
		return "admin/home"
	}
}