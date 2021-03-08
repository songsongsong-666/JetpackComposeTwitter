package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.darkThemeColors
import com.sudo.rizwan.twitterclone.darkerThemeColors
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.sudorizwan
import com.sudo.rizwan.twitterclone.ui.common.CustomDivider
import com.sudo.rizwan.twitterclone.ui.common.ThemedText
import com.sudo.rizwan.twitterclone.ui.common.UserInfo

@Composable
fun AppDrawer() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    val showThemeDialog = remember { mutableStateOf(false) }
    Surface(color = theme.value!!.surface) {
        Column {
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
                Image(
                    painterResource(R.drawable.profile_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(25.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(2.dp))
                UserInfo(sudorizwan)
                Spacer(modifier = Modifier.height(16.dp))
            }
            CustomDivider()
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                SideBarListItem(
                    text = "Lists",
                    icon = R.drawable.ic_lists
                )
                SideBarListItem(
                    text = "Topics",
                    icon = R.drawable.ic_topics
                )
                SideBarListItem(
                    text = "Bookmarks",
                    icon = R.drawable.ic_bookmarks
                )
                SideBarListItem(
                    text = "Moments",
                    icon = R.drawable.ic_moments
                )
            }
            CustomDivider()
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                ThemedText(
                    text = "Settings and privacy",
                    style = TextStyle(fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ThemedText(
                    text = "Help Center",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            CustomDivider()
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.padding(all = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { showThemeDialog.value = true }) {
                    Image(
                        painterResource(R.drawable.ic_theme),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Button(
                    modifier = Modifier.padding(all = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = {}) {
                    Image(
                        painterResource(R.drawable.ic_qrcode),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        if (showThemeDialog.value) {
            Dialog(onDismissRequest = { showThemeDialog.value = false }) {
                Surface(
                    modifier = Modifier.width(300.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = theme.value!!.surface
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        ThemedText(text = "Pick a theme", style = TextStyle(fontSize = 22.sp))
                        Spacer(modifier = Modifier.height(16.dp))
                        ThemeOption("Light", theme.value == lightThemeColors) {
                            // on click
                            appState.theme.value = lightThemeColors
                        }
                        ThemeOption("Dark", theme.value == darkThemeColors) {
                            // on click
                            appState.theme.value = darkThemeColors
                        }
                        ThemeOption("Darker", theme.value == darkerThemeColors) {
                            // on click
                            appState.theme.value = darkerThemeColors
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SideBarListItem(text: String, icon: Int) {
    Row(modifier = Modifier.height(50.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        ThemedText(
            text = text,
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
private fun ThemeOption(text: String, selected: Boolean, onSelect: () -> Unit) {
    Button(
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier.height(34.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = onSelect,
                // TODO colors = AppState.theme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            ThemedText(text = text)
        }
    }
}