package com.hyecheon.springsecuritystudy.controller.admin

import com.hyecheon.springsecuritystudy.domain.dto.AccountDto
import com.hyecheon.springsecuritystudy.domain.entity.Account
import com.hyecheon.springsecuritystudy.domain.entity.Role
import com.hyecheon.springsecuritystudy.service.RoleService
import com.hyecheon.springsecuritystudy.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping


@Controller
class UserManagerController(
		private val userService: UserService,
		private val roleService: RoleService) {

	@GetMapping(value = ["/admin/accounts"])
	fun getUsers(model: Model): String? {
		val accounts: List<Account> = userService.getUsers()
		model.addAttribute("accounts", accounts)
		return "admin/user/list"
	}

	@PostMapping(value = ["/admin/accounts"])
	@Throws(Exception::class)
	fun modifyUser(accountDto: AccountDto): String {
		userService.modifyUser(accountDto)
		return "redirect:/admin/accounts"
	}

	@GetMapping(value = ["/admin/accounts/{id}"])
	fun getUser(@PathVariable(value = "id") id: Long, model: Model): String {
		val account = userService.getUser(id)
		val accountDto = AccountDto(id = account.id, age = account.age.toString(), email = account.email, username = account.username, roles = account.userRoles.map { role ->
			role.roleName ?: ""
		}.filter { it.isNotEmpty() })

		val roleList: List<Role> = roleService.getRoles()
		model.addAttribute("account", accountDto)
		model.addAttribute("roleList", roleList)
		return "admin/user/detail"
	}

	@GetMapping(value = ["/admin/accounts/delete/{id}"])
	fun removeUser(@PathVariable(value = "id") id: Long, model: Model): String {
		userService.deleteUser(id)
		return "redirect:/admin/users"
	}
}