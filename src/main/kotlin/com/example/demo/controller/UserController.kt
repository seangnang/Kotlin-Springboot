package com.example.demo.controller

import com.example.demo.Entity.User
import com.example.demo.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "User management APIs")
class UserController(
    private val userRepository: UserRepository
) {
    @GetMapping
    @Operation(summary = "Get all users")
    fun getAllUsers(): List<User> =
        userRepository.findAll()

    @PostMapping
    @Operation(summary = "Create a new user")
    fun createUser(@RequestBody user: User): User =
        userRepository.save(user)

    // --- Added Edit (Update) ---
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    fun updateUser(@PathVariable id: Long, @RequestBody userDetails: User): ResponseEntity<User> {
        return userRepository.findById(id).map { existingUser ->
            // Assuming your User entity has these fields (adjust as necessary)
            val updatedUser = existingUser.copy(
                name = userDetails.name,
                email = userDetails.email
            )
            ResponseEntity.ok(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    // --- Added Delete ---
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @Configuration
    class OpenApiConfig {
        @Bean
        fun customOpenAPI(): OpenAPI =
            OpenAPI().info(
                Info()
                    .title("Spring Boot Kotlin API")
                    .version("1.0")
                    .description("API with SQL Server and Swagger")
            )
    }
}