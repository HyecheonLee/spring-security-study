package com.hyecheon.springsecuritystudy.note

import com.hyecheon.springsecuritystudy.user.User
import com.hyecheon.springsecuritystudy.user.UserNotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Service
class NoteService(
    private val noteRepository: NoteRepository,
) {
    @Transactional(readOnly = true)
    fun findByUser(user: User?) = run {
        if (user == null) throw UserNotFoundException()
        if (user.isAdmin()) noteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
        else noteRepository.findByUserOrderByIdDesc(user)
    }

    fun saveNote(user: User?, title: String, content: String) = run {
        if (user == null) throw UserNotFoundException()
        noteRepository.save(Note(title = title, content = content, user = user))
    }

    fun deleteNote(user: User?, noteId: Long) = run {
        if (user == null) throw UserNotFoundException()
        noteRepository.findByIdAndUser(noteId, user)?.let {
            noteRepository.delete(it)
        }
    }
}