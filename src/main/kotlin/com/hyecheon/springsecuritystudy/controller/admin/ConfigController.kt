package com.hyecheon.springsecuritystudy.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ConfigController {

	@GetMapping(value = ["/config"])
	fun config(): String {
		return "admin/config"
	}
}