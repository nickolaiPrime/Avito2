package com.example.avito2.domain.model

enum class SortOrder{
    NEW,CHEAP,EXPENSIVE
}

data class AdFilters(
    val category: String?,
    val minPrice: Long?,
    val maxPrice: Long?,
    val sortOrder: SortOrder
)