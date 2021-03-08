package com.sudo.rizwan.twitterclone.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel

@Composable
fun ThemedText(text: String, style: TextStyle = TextStyle()) {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Text(
        text = text,
        style = style,
        color = if (theme.value!!.isLight) Color(0xFF111111) else Color(0xFFEEEEEE)
    )
}


@Composable
fun GrayText(text: String) {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            color = if (theme.value!!.isLight) Color(0xFF666666) else Color(0xFFDDDDDD)
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
