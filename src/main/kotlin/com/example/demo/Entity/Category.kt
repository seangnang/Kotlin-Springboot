package com.example.demo.Entity

import jakarta.persistence.*
@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @OneToMany(
        mappedBy = "category",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val products: MutableList<Product> = mutableListOf()
)