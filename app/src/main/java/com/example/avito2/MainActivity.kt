package com.example.avito2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.avito2.presentation.feed.FeedScreen
import com.example.avito2.presentation.feed.FeedViewModel
import com.example.avito2.ui.theme.Avito2Theme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Avito2Theme {
                FeedScreen(
                    viewModel = koinViewModel<FeedViewModel>()
                )
            }
        }
    }
}
