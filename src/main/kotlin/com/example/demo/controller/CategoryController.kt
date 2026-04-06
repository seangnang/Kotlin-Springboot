package com.example.demo.controller

import com.example.demo.Entity.Category
import com.example.demo.dto.CategoryRequest
import com.example.demo.repository.CategoryRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "Category management")
class CategoryController(
    private val categoryRepository: CategoryRepository
) {
    // CREATE
    @PostMapping
    fun create(@RequestBody req: CategoryRequest): Category =
        categoryRepository.save(Category(name = req.name))
    // LIST
    @GetMapping
    fun list(): List<Category> =
        categoryRepository.findAll()
    // UPDATE
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody req: CategoryRequest
    ): Category {
        val category = categoryRepository.findById(id)
            .orElseThrow { RuntimeException("Category not found") }
        return categoryRepository.save(
            category.copy(name = req.name)
        )
    }
    // DELETE
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        categoryRepository.deleteById(id)
}