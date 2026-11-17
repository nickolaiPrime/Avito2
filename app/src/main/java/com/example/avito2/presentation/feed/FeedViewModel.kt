package com.example.avito2.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.avito2.domain.repository.AdsRepository

class FeedViewModel(private val repository : AdsRepository) : ViewModel() {
    val ads = repository.getAds().cachedIn(viewModelScope)
}