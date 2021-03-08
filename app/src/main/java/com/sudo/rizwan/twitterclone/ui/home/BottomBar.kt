package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel

@Composable
fun BottomBar() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    BottomAppBar(backgroundColor = theme.value!!.surface) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomBarIcon(R.drawable.ic_home_selected)
            BottomBarIcon(R.drawable.ic_search)
            BottomBarIcon(R.drawable.ic_notifications)
            BottomBarIcon(R.drawable.ic_dm)
        }
    }
}

@Composable
private fun BottomBarIcon(icon: Int) {
    IconButton(onClick = {}) {
        Image(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}