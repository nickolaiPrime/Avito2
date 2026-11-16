package com.example.avito2.data.local.dto
import kotlinx.serialization.Serializable

@Serializable
data class AdDto(
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val category: String,
    val imageUrls: List<String>,
    val location: String,
    val createdAt: String
)