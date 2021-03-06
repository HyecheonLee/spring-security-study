package com.hyecheon.springsecuritystudy.aopsecurity

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class AopSecurityController {
	@Autowired
	lateinit var aopMethodService: AopMethodService

	@GetMapping("/preAuthorize")
	@PreAuthorize("hasRole('ROLE_USEr') and #accountDto.username == principal.username")
	fun preAuthorize(accountDto: AccountDto?, model: Model, principal: Principal?): String {

		model.addAttribute("method", "Success @PreAuthorize")

		return "aop/method"

	}

	@GetMapping("/methodSecured")
	fun methodSecured(model: Model) = let {
		aopMethodService.methodSecured()
		model.addAttribute("method", "Success MethodSecured")

		"aop/method"
	}
}