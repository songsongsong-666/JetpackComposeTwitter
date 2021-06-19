package com.sudo.rizwan.twitterclone.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudo.rizwan.twitterclone.models.User

@Composable
fun UserInfo(user: User, showBio: Boolean = false) {
    ThemedText(
        text = user.name,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
    )
    ThemedText(text = "@${user.username}")
    if (showBio) {
        Spacer(modifier = Modifier.height(8.dp))
        ThemedText(
            text = user.bio,
            style = TextStyle(fontSize = 14.sp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThemedText(
            text = "${user.following} ",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        ThemedText(
            text = "Following",
            style = TextStyle(fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        ThemedText(
            text = "${user.followers} ",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        ThemedText(
            text = "Followers",
            style = TextStyle(fontSize = 14.sp)
        )
    }
}