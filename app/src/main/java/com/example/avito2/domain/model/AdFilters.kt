package com.example.avito2.domain.model

enum class SortOrder{
    PRICE_ASC, PRICE_DESС, NEW
}

data class AdFilters(
    val category: String? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val sortOrder: SortOrder = SortOrder.NEW
)