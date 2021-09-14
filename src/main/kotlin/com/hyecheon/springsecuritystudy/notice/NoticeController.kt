package com.hyecheon.springsecuritystudy.notice

import com.hyecheon.springsecuritystudy.note.NoteRegisterDto
import com.hyecheon.springsecuritystudy.user.User
import org.springframework.security.core.Authentication
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
class NoticeController(
    private val noticeService: NoticeService,
) {
    @GetMapping
    fun getNotice(model: Model) = run {
        val notices = noticeService.findAll()
        model.addAttribute("notices", notices)
        "notice/index"
    }

    @PostMapping
    fun postNotice(@ModelAttribute noteDto: NoteRegisterDto) = run {
        noticeService.saveNotice(noteDto.title, noteDto.content)
        "redirect:notice"
    }

    fun deleteNotice(@RequestParam id: Long) = run {
        noticeService.deleteNotice(id)
        "redirect:notice"
    }
}