package com.example.avito2.presentation.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.avito2.presentation.feed.components.AdCard
import com.example.avito2.presentation.feed.components.CategorySelector
import com.example.avito2.presentation.feed.components.FeedHeader

@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    modifier: Modifier = Modifier
) {
    val ads = viewModel.ads.collectAsLazyPagingItems()
    var query by rememberSaveable {
        mutableStateOf("")
    }

    var selectedCategory by rememberSaveable {
        mutableStateOf("Все")
    }

    val categories = listOf(
        "Все",
        "Электроника",
        "Дом",
        "Авто",
        "Одежда",
        "Хобби",
        "Услуги"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        FeedHeader(
            query = query,
            onQueryChange = { newQuery ->
                query = newQuery
            }
        )

        CategorySelector(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = {category ->
                selectedCategory = category
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = ads.itemCount,
                key = ads.itemKey { ad -> ad.id }
            ) { index ->

                val ad = ads[index]

                if (ad != null) {
                    AdCard(
                        ad = ad,
                        isFavorite = false,
                        onFavoriteClick = {},
                        onClick = {}
                    )
                }
            }
        }
    }
}