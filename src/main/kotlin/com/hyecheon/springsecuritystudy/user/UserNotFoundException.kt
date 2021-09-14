package com.hyecheon.springsecuritystudy.user

import java.lang.RuntimeException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
class UserNotFoundException(message: String) : RuntimeException(message) {
    constructor() : this("유저를 찾을 수 없습니다.")
}