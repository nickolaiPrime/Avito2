package com.example.avito2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.avito2.data.local.JsonAdsDataSource
import com.example.avito2.data.mapper.toDomain
import com.example.avito2.data.paging.AdsPagingSource
import com.example.avito2.domain.model.Ad
import com.example.avito2.domain.repository.AdsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AdsRepositoryImpl(
    private val source : JsonAdsDataSource
) : AdsRepository {
    override fun getAds(): Flow<PagingData<Ad>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 60,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                AdsPagingSource(source)
            }
        ).flow.map { pagingData ->
            pagingData.map { dto ->
                dto.toDomain()
            }
        }
    }
}