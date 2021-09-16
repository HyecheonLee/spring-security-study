package com.hyecheon.springsecuritystudy.notice

import com.hyecheon.springsecuritystudy.note.Note
import com.hyecheon.springsecuritystudy.note.NoteRepository
import com.hyecheon.springsecuritystudy.user.User
import com.hyecheon.springsecuritystudy.user.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.TestExecutionEvent
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/17
 */
@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
internal class NoticeControllerTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var noteRepository: NoteRepository
    lateinit var mockMvc: MockMvc
    lateinit var user: User
    lateinit var admin: User

    @BeforeEach
    internal fun setUp(applicationContext: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .alwaysDo<DefaultMockMvcBuilder>(print())
            .build()
        user = userRepository.save(User(username = "user123", password = "user", authority = "ROLE_USER"))
        admin = userRepository.save(User(username = "admin123", password = "admin", authority = "ROLE_ADMIN"))

    }


    @DisplayName("1. getNote 인증없음")
    @Test
    internal fun test1() {
        mockMvc.perform(get("/note"))
            .andExpect(redirectedUrlPattern("**/login"))
            .andExpect(status().is3xxRedirection)

    }

    @DisplayName("2. getNote 인증있음")
    @WithUserDetails(
        value = "user123",
        userDetailsServiceBeanName = "userDetailsService",
        setupBefore = TestExecutionEvent.TEST_EXECUTION
    )
    @Test
    internal fun test2() {
        mockMvc.perform(
            get("/note")
        )
            .andExpect(status().isOk)
            .andExpect(view().name("note/index"))
            .andDo(print())

    }

    @DisplayName("3. postNote 인증없음")
    @Test
    internal fun test3() {

        mockMvc.perform(
            post("/note").with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "제목")
                .param("content", "내용")
        ).andExpect(redirectedUrlPattern("**/login"))
            .andExpect(status().is3xxRedirection)
    }

    @DisplayName("4. postNote_어드민인증있음")
    @Test
    @WithUserDetails(value = "admin123",
        userDetailsServiceBeanName = "userDetailsService",
        setupBefore = TestExecutionEvent.TEST_EXECUTION)
    fun test4() {
        mockMvc.perform(
            post("/note").with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "제목")
                .param("content", "내용")
        ).andExpect(status().isForbidden) // 접근 거부
    }


    @DisplayName("5. postNote 유저인증있음")
    @WithUserDetails(
        value = "user123",
        userDetailsServiceBeanName = "userDetailsService",
        setupBefore = TestExecutionEvent.TEST_EXECUTION
    )
    @Test
    internal fun test5() {
        mockMvc.perform(
            post("/note").with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "제목")
                .param("content", "내용")
        ).andExpect(redirectedUrl("note")).andExpect(status().is3xxRedirection);
    }


    @DisplayName("6. deleteNote_인증없음")
    @Test
    internal fun test6() {
        val note = noteRepository.save(Note(title = "제목", content = "내용", user = user))
        mockMvc.perform(
            delete("/note?id=${note.id}").with(csrf())
        ).andExpect(redirectedUrlPattern("**/login"))
            .andExpect(status().is3xxRedirection)
    }


    @DisplayName("7. deleteNote 유저인증있음")
    @Test
    @WithUserDetails(value = "user123",
        userDetailsServiceBeanName = "userDetailsService",
        setupBefore = TestExecutionEvent.TEST_EXECUTION)
    fun test7() {
        val note = noteRepository.save(Note(title = "제목", content = "내용", user = user))
        mockMvc.perform(
            delete("/note?id=" + note.id).with(csrf())
        ).andExpect(redirectedUrl("note")).andExpect(status().is3xxRedirection)
    }

    @DisplayName("8. deleteNote 어드민인증있음")
    @Test
    @WithUserDetails(value = "admin123",
        userDetailsServiceBeanName = "userDetailsService",
        setupBefore = TestExecutionEvent.TEST_EXECUTION)
    fun test8() {
        val note = noteRepository.save(Note(title = "제목", content = "내용", user = user))
        mockMvc.perform(
            delete("/note?id=" + note.id).with(csrf()).with(user(admin))
        ).andExpect(status().isForbidden) // 접근 거부
    }
}