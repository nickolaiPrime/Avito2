
package com.example.avito2.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.avito2.domain.repository.AdsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class FeedViewModel(
    private val repository: AdsRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val selectedCategory = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val ads = combine(
        searchQuery,
        selectedCategory
    ) { query, category ->
        query to category
    }.flatMapLatest { (query, category) ->
        repository.getAds(query, category)
    }.cachedIn(viewModelScope)

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun updateCategory(category: String?) {
        selectedCategory.value = category
    }
}
