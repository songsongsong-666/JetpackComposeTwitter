package com.sudo.rizwan.twitterclone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.Screen
import com.sudo.rizwan.twitterclone.ui.compose.ComposeTweet
import com.sudo.rizwan.twitterclone.ui.home.Home
import com.sudo.rizwan.twitterclone.ui.profile.Profile

class MainActivity : AppCompatActivity() {
    private val appState by viewModels<AppStateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = appState.theme.observeAsState(lightThemeColors)

            MaterialTheme(colors = theme.value) {
                AppContent()
            }
        }
    }

    override fun onBackPressed() {
        if (appState.currentScreen.value !is Screen.Home) {
            // Temp handling of back navigation
            appState.currentScreen.value = Screen.Home
            return
        }
        super.onBackPressed()
    }
}

@Composable
fun AppContent() {
    val appState = viewModel<AppStateViewModel>()
    val currentScreen = appState.currentScreen.observeAsState(Screen.Home)

    Crossfade(currentScreen.value) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Home -> Home()
                is Screen.Profile -> Profile(
                    screen.user
                )
                is Screen.Compose -> ComposeTweet()
            }
        }
    }
}
