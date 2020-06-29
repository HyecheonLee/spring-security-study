package com.hyecheon.springsecuritystudy.aopsecurity

import org.springframework.stereotype.Service

@Service
class AopMethodService {

	fun methodSecured() {
		println("methodSecured")
	}

}