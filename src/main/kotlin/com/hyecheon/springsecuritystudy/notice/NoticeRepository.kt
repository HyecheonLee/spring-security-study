package com.hyecheon.springsecuritystudy.notice

import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
interface NoticeRepository : JpaRepository<Notice, Long> {
}