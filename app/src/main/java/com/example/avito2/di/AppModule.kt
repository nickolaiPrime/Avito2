package com.example.avito2.di

import com.example.avito2.data.local.JsonAdsDataSource
import com.example.avito2.data.repository.AdsRepositoryImpl
import com.example.avito2.domain.repository.AdsRepository
import com.example.avito2.presentation.feed.FeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { JsonAdsDataSource(androidContext()) }
    single<AdsRepository>{ AdsRepositoryImpl(get()) }
    viewModel{ FeedViewModel(get()) }
}