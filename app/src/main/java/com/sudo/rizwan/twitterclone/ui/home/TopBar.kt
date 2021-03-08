package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import kotlinx.coroutines.launch

@Composable
fun TopBar(scaffoldState: ScaffoldState) {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)
    val coroutineScope = rememberCoroutineScope()

    Surface(elevation = 2.dp, color = theme.value!!.surface) {
        Row(
            modifier = Modifier.height(50.dp).padding(start = 16.dp, end = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.clickable(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            ) {
                Image(
                    painterResource(R.drawable.profile_image),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp).clip(shape = RoundedCornerShape(17.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Image(painterResource(R.drawable.ic_twitter), contentDescription = null, modifier = Modifier.size(22.dp))
            Image(painterResource(R.drawable.ic_trends), contentDescription = null, modifier = Modifier.size(24.dp))
        }
    }
}
