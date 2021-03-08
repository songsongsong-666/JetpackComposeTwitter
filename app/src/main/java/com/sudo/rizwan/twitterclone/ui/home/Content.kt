package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sudo.rizwan.twitterclone.state.tweets
import com.sudo.rizwan.twitterclone.ui.common.CustomDivider
import com.sudo.rizwan.twitterclone.ui.common.TweetLayout

@Composable
fun Content(scaffoldState: ScaffoldState) {
    Column(modifier = Modifier.padding(bottom = 54.dp)) {
        TopBar(scaffoldState = scaffoldState)
        LazyColumn {
            items(tweets) { tweet ->
                TweetLayout(tweet)
                CustomDivider()
            }
        }
    }
}
