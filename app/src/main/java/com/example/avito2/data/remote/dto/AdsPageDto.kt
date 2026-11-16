package com.example.avito2.data.remote.dto

data class AdsPageDto(
    val items: List<AdDto>,
    val page: Int,
    val total: Long,
    val hasNext: Boolean
)