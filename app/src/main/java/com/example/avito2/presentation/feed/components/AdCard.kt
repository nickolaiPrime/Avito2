package com.example.avito2.presentation.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.avito2.domain.model.Ad
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AdCard(
    ad: Ad,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            val imageUrl = ad.imageUrls.firstOrNull()

            if(imageUrl != null){
                AsyncImage(
                    model = imageUrl,
                    contentDescription = ad.title,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(color = MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ){
                    Text("Нет фото", color = MaterialTheme.colorScheme.onBackground)
                }
            }

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val price = NumberFormat
                        .getNumberInstance(Locale.forLanguageTag("ru-RU"))
                        .format(ad.price) + " ₽"

                    Text(text = price, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.offset(x = 12.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = if (isFavorite) {
                                "Убрать из избранного"
                            } else {
                                "Добавить в избранное"
                            },
                            tint = if (isFavorite) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.secondary
                            }
                        )
                    }
                }

                Text(text = ad.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis)
                Text(text = ad.location, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}