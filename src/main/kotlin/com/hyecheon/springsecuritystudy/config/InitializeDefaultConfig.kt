package com.hyecheon.springsecuritystudy.config

import com.hyecheon.springsecuritystudy.note.NoteService
import com.hyecheon.springsecuritystudy.notice.NoticeService
import com.hyecheon.springsecuritystudy.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Configuration
@Profile(value = ["!test"])
class InitializeDefaultConfig(
    private val userService: UserService,
    private val noteService: NoteService,
    private val noticeService: NoticeService,
) {

    @Bean
    fun initializeDefaultUser() = run {
        val user = userService.signup("user", "user")
        noteService.saveNote(user, "테스트", "테스트입니다.")
        noteService.saveNote(user, "테스트2", "테스트2입니다.")
        noteService.saveNote(user, "테스트3", "테스트3입니다.")
        noteService.saveNote(user, "여름 여행계획", "여름 여행계획 작성중...")
    }

    fun initializeDefaultAdmin() = run {
        userService.signupAdmin("admin", "admin")
        noticeService.saveNotice("환영합니다.", "환영합니다 여러분")
        noticeService.saveNotice("노트 작성 방법 공지", "1. 회원가입.\n2. 로그인\n3. 노트작성\n4. 저장\n* 본인ㅇ 외에는 게시글을 볼 수 없습니다.")
        
    }
}