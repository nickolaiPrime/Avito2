package com.example.avito2.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.avito2.data.local.JsonAdsDataSource
import com.example.avito2.data.local.dto.AdDto
import kotlinx.coroutines.CancellationException

class AdsPagingSource(
    private val source: JsonAdsDataSource,
    private val query: String,
    private val category: String?
) : PagingSource<Int, AdDto>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, AdDto> {
        try {
            val allAds = source.getAds()
            val normalizedQuery = query.trim()

            val filteredAds = allAds.filter { ad ->

                val matchesQuery =
                    normalizedQuery.isBlank() ||
                            ad.title.contains(normalizedQuery, ignoreCase = true) ||
                            ad.description.contains(normalizedQuery, ignoreCase = true) ||
                            ad.location.contains(normalizedQuery, ignoreCase = true)

                val matchesCategory =
                    category == null || ad.category == category

                matchesQuery && matchesCategory
            }

            val startIndex = (params.key ?: 0)
                .coerceIn(0, filteredAds.size)

            val endIndex = minOf(
                startIndex + params.loadSize,
                filteredAds.size
            )

            val pageData = filteredAds.subList(startIndex, endIndex)

            val prevKey = if (startIndex > 0) {
                (startIndex - params.loadSize).coerceAtLeast(0)
            } else {
                null
            }

            val nextKey = if (endIndex < filteredAds.size) {
                endIndex
            } else {
                null
            }

            return LoadResult.Page(
                data = pageData,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, AdDto>
    ): Int? {
        val currentPos = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(currentPos)

        closestPage?.let { page ->
            page.prevKey?.let { prevKey ->
                return prevKey + state.config.pageSize
            }

            page.nextKey?.let { nextKey ->
                return nextKey - page.data.size
            }
        }
        return null
    }
}