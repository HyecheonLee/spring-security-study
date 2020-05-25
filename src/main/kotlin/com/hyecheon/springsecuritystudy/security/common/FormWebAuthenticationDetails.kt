package com.hyecheon.springsecuritystudy.security.common

import org.springframework.security.web.authentication.WebAuthenticationDetails
import javax.servlet.http.HttpServletRequest


class FormWebAuthenticationDetails(request: HttpServletRequest) : WebAuthenticationDetails(request) {
	val secretKey: String?

	init {
		secretKey = request.getParameter("secret_key")
	}
}
