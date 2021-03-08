package com.sudo.rizwan.twitterclone.ui.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel

@Composable
fun CustomDivider() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Divider(color = if (theme.value!!.isLight) Color(0xFFEEEEEE) else Color(0xFF333333))
}