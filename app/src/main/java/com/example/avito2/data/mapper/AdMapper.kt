package com.example.avito2.data.mapper

import com.example.avito2.data.local.dto.AdDto
import com.example.avito2.domain.model.Ad

fun AdDto.toDomain() : Ad {
    return Ad(
        id = id,
        title = title,
        description = description,
        price = price,
        category = category,
        imageUrls = imageUrls,
        location = location,
        createdAt = createdAt
    )
}