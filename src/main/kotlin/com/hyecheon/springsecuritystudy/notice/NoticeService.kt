package com.hyecheon.springsecuritystudy.notice

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Transactional
@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
) {

    @Transactional(readOnly = true)
    fun findAll() = run {
        noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
    }

    fun saveNotice(title: String, content: String) = run {
        noticeRepository.save(Notice(title = title, content = content))
    }

    fun deleteNotice(id: Long) = run {
        noticeRepository.findById(id).ifPresent { noticeRepository.delete(it) }
    }

}