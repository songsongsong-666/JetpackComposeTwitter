package com.sudo.rizwan.twitterclone.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.models.Tweet
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.Screen

@Composable
fun TweetLayout(tweet: Tweet) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        UserAvatar(tweet)
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            NameAndUserName(tweet)
            Spacer(modifier = Modifier.size(1.dp))
            TweetAndImage(tweet)
            Spacer(modifier = Modifier.size(10.dp))
            TweetActions(tweet)
        }
    }
}

@Composable
private fun TweetActions(tweet: Tweet) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val imageSize = 18.dp
        Row {
            Image(
                painterResource(R.drawable.ic_comment),
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )
            Spacer(modifier = Modifier.size(4.dp))
            GrayText(text = tweet.comments.toString())
        }

        Row(
            modifier = Modifier.clickable(
                onClick = { tweet.retweet() }
            )
        ) {
            Image(
                painterResource(if (tweet.retweeted) R.drawable.ic_retweeted else R.drawable.ic_retweet),
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )
            Spacer(modifier = Modifier.size(4.dp))
            GrayText(text = tweet.retweets.toString())
        }

        Row(
            modifier = Modifier.clickable(
                onClick = { tweet.like() }
            )
        ) {
            Image(
                painterResource(if (tweet.liked) R.drawable.ic_liked else R.drawable.ic_like),
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )
            Spacer(modifier = Modifier.size(4.dp))
            GrayText(text = tweet.likes.toString())
        }

        Image(
            painterResource(R.drawable.ic_share),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
private fun TweetAndImage(tweet: Tweet) {
    ThemedText(
        text = tweet.tweet,
        style = TextStyle(fontSize = 14.sp)
    )
    if (tweet.image != null) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painterResource(tweet.image),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(2.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun NameAndUserName(tweet: Tweet) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ThemedText(
            text = tweet.user.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        if (tweet.user.verified) {
            Spacer(modifier = Modifier.size(2.dp))
            Image(
                painterResource(R.drawable.ic_verified),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        GrayText(text = "@${tweet.user.username} · ${tweet.timeAgo()}")
    }
}

@Composable
private fun UserAvatar(tweet: Tweet) {
    val appState = viewModel<AppStateViewModel>()

    Box(
        modifier = Modifier.clickable(
            onClick = { appState.navigateTo(Screen.Profile(tweet.user)) }
        )
    ) {
        Image(
            painterResource(tweet.user.avatar),
            contentDescription = null,
            modifier = Modifier.size(50.dp).clip(shape = RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
