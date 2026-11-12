package com.example.avito2.domain.model

data class Ad(
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val category: String,
    val imageUrls: List<String>,
    val location: String,
    val createdAt: String
)