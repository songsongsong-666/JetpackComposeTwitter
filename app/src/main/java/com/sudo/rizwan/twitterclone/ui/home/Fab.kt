package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.Screen

@Composable
fun Fab() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    FloatingActionButton(
        onClick = { appState.navigateTo(Screen.Compose) },
        backgroundColor = theme.value!!.primary
    ) {
        Image(
            painterResource(R.drawable.ic_compose),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
    }
}
