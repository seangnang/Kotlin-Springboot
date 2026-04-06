package com.example.demo.controller

import com.example.demo.Entity.Product
import com.example.demo.dto.ProductRequest
import com.example.demo.repository.CategoryRepository
import com.example.demo.repository.ProductRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Product management")
class ProductController(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {
    // CREATE
    @PostMapping
    fun create(@RequestBody req: ProductRequest): Product {
        val category = categoryRepository.findById(req.categoryId)
            .orElseThrow { RuntimeException("Category not found") }
        return productRepository.save(
            Product(
                name = req.name,
                price = req.price,
                category = category
            )
        )
    }
    // LIST
    @GetMapping
    fun list(): List<Product> =
        productRepository.findAll()
    // UPDATE
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody req: ProductRequest
    ): Product {
        val product = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }
        val category = categoryRepository.findById(req.categoryId)
            .orElseThrow { RuntimeException("Category not found") }
        return productRepository.save(
            product.copy(
                name = req.name,
                price = req.price,
                category = category
            )
        )
    }
    // DELETE
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        productRepository.deleteById(id)
}