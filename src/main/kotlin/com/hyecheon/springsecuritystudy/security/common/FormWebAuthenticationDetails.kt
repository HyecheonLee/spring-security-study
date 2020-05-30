package com.hyecheon.springsecuritystudy.security.common

import org.springframework.security.web.authentication.WebAuthenticationDetails
import javax.servlet.http.HttpServletRequest


class FormWebAuthenticationDetails(request: HttpServletRequest) : WebAuthenticationDetails(request) {
	val secretKey: String? = request.getParameter("secret_key")

}
