package com.hyecheon.springsecuritystudy.note

import com.hyecheon.springsecuritystudy.user.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
interface NoteRepository : JpaRepository<Note, Long> {
    fun findByUserOrderByIdDesc(user: User): MutableList<Note>
    fun findByIdAndUser(noteId: Long, user: User): Note?
}