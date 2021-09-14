package com.hyecheon.springsecuritystudy.note

import com.hyecheon.springsecuritystudy.user.User
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Controller
@RequestMapping("/note")
class NoteController(
    private val noteService: NoteService,
) {
    @GetMapping
    fun getNote(authentication: Authentication, model: Model) = run {
        val user = authentication.principal
        if (user is User) {
            val notes = noteService.findByUser(user)
            model.addAttribute("notes", notes)
        }
        "note/index"
    }

    @PostMapping
    fun saveNote(authentication: Authentication, @ModelAttribute noteDto: NoteRegisterDto) = run {
        val user = authentication.principal
        if (user is User) {
            noteService.saveNote(user = user, title = noteDto.title, content = noteDto.content)
        }
        "redirect:note"
    }

    @DeleteMapping
    fun deleteNote(authentication: Authentication, @RequestParam id: Long) = run {
        val user = authentication.principal
        if (user is User) {
            noteService.deleteNote(user, id)
        }
        "redirect:note"
    }
}