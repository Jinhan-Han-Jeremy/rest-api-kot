package org.github.kotlinblog.controller

import org.github.kotlinblog.entity.User
import org.github.kotlinblog.entity.UserRole
import org.github.kotlinblog.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class ReadUserResDto (
    val id: Long?,
    val email: String,
    var roles: MutableSet<UserRole>? = null,
)

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService){
    @GetMapping()
    fun readUser(auth: Authentication): User {
        val userId = auth.principal.toString().toLong()
        return userService.readUserById(userId)
    }
}