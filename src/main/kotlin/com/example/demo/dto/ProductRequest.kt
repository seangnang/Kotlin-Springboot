package com.example.demo.dto

data class ProductRequest(
    val name: String,
    val price: Double,
    val categoryId: Long
)