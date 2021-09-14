package com.hyecheon.springsecuritystudy.admin

import com.hyecheon.springsecuritystudy.note.NoteService
import com.hyecheon.springsecuritystudy.user.User
import org.springframework.security.core.Authentication

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Controller
@RequestMapping("/admin")
class AdminController(
    private val noteService: NoteService,
) {
    @GetMapping
    fun getNoteForAdmin(authentication: Authentication, model: Model) = run {
        val user = authentication.principal
        if (user is User) {
            val notes = noteService.findByUser(user = user)
            model.addAttribute("notes", notes)
        }
        "admin/index"
    }
}