package com.example.avito2.domain.repository

import androidx.paging.PagingData
import com.example.avito2.domain.model.Ad
import kotlinx.coroutines.flow.Flow

interface AdsRepository {
    fun getAds(
        query: String = "",
        category: String? = null
    ): Flow<PagingData<Ad>>
}