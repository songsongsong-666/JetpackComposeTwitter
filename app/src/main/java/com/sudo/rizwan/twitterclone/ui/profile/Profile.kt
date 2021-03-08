package com.sudo.rizwan.twitterclone.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.models.User
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.Screen
import com.sudo.rizwan.twitterclone.state.tweets
import com.sudo.rizwan.twitterclone.ui.common.CustomDivider
import com.sudo.rizwan.twitterclone.ui.common.TweetLayout
import com.sudo.rizwan.twitterclone.ui.common.UserInfo

@Composable
fun Profile(user: User) {
    Box(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        ConstraintLayout(constraintSet = ConstraintSet {
            val banner = createRefFor("banner")
            val closeButton = createRefFor("close")
            val moreButton = createRefFor("more")
            val avatar = createRefFor("avatar")
            val content = createRefFor("content")
            val followButton = createRefFor("follow")
            constrain(avatar) {
                top.linkTo(banner.bottom)
                bottom.linkTo(banner.bottom)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(followButton) {
                top.linkTo(banner.bottom, 16.dp)
                end.linkTo(parent.end, 16.dp)
            }
            constrain(closeButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            constrain(moreButton) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
            constrain(content) {
                top.linkTo(banner.bottom)
            }
        }) {
            Banner(user)
            FollowButton()
            TopBar()
            Avatar(user)
            ProfileContent(user)
        }
    }
}

@Composable
private fun ProfileContent(user: User) {
    Column(modifier = Modifier.layoutId("content")) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(modifier = Modifier.height(44.dp))
            UserInfo(
                user = user,
                showBio = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        CustomDivider()
        tweets.filter { it.user == user }.forEach { tweet ->
            TweetLayout(tweet)
            CustomDivider()
        }
    }
}

@Composable
private fun Avatar(user: User) {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Image(
        painterResource(user.avatar),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .layoutId("avatar")
            .border(
                border = BorderStroke(3.dp, color = theme.value!!.surface),
                shape = RoundedCornerShape(40.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun TopBar() {
    val appState = viewModel<AppStateViewModel>()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.layoutId("close"),
            onClick = { appState.navigateTo(Screen.Home) }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }

        IconButton(modifier = Modifier.layoutId("more"), onClick = {}) {
            Icon(Icons.Filled.MoreVert, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
private fun FollowButton() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Button(
        modifier = Modifier.layoutId("follow"),
        onClick = {
        },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = theme.value!!.surface
        ),
        border = BorderStroke(1.dp, theme.value!!.primary)
    ) {
        Text(text = "Follow", color = theme.value!!.primary)
    }
}

@Composable
private fun Banner(user: User) {
    Image(
        painterResource(user.banner),
        contentDescription = null,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .layoutId("banner"),
        contentScale = ContentScale.Crop
    )
}
