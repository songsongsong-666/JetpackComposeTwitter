package com.sudo.rizwan.twitterclone.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.models.User

sealed class Screen {
    object Home : Screen()
    data class Profile(val user: User) : Screen()
    object Compose : Screen()
}

class AppStateViewModel : ViewModel() {
    val currentScreen = MutableLiveData<Screen>(Screen.Home)
    val theme = MutableLiveData(lightThemeColors)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
