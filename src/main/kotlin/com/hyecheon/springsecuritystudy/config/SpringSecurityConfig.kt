package com.hyecheon.springsecuritystudy.config

import com.hyecheon.springsecuritystudy.user.UserService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/09/14
 */
@Configuration
@EnableWebSecurity
class SpringSecurityConfig(
    private val userService: UserService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
        http.csrf()
        http.rememberMe()
        http.authorizeRequests()
            .antMatchers("/", "/home", "/signup").permitAll()
            .antMatchers("/note").hasRole("USER")
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/notice").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/notice").hasRole("ADMIN")
            .anyRequest().authenticated()

        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
        http.logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    @Bean
    override fun userDetailsService() = run {
        UserDetailsService { username ->
            val user = userService.findByUsername(username)
            user
        }
    }
}