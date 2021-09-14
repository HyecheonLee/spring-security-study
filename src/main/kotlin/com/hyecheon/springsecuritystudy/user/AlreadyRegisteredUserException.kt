package com.hyecheon.springsecuritystudy.user

import java.lang.RuntimeException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
class AlreadyRegisteredUserException(message: String? = "이미 등록된 유저입니다.") : RuntimeException(message) {

}