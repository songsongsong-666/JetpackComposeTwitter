package com.sudo.rizwan.twitterclone.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudo.rizwan.twitterclone.R
import com.sudo.rizwan.twitterclone.lightThemeColors
import com.sudo.rizwan.twitterclone.state.AppStateViewModel
import com.sudo.rizwan.twitterclone.state.Screen
import com.sudo.rizwan.twitterclone.state.createNewTweet

@Composable
fun ComposeTweet() {
    val tweetText = remember { mutableStateOf(TextFieldValue(text = "")) }
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CloseButton()
            TweetButton(tweetText)
        }
        AvatarWithTextField(tweetText)
    }
}

@Composable
private fun AvatarWithTextField(tweetText: MutableState<TextFieldValue>) {
    Row(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Image(
            painterResource(R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .clip(shape = RoundedCornerShape(17.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextFieldWithHint(
            modifier = Modifier.fillMaxWidth(),
            value = tweetText.value,
            onValueChange = { textFieldValue -> tweetText.value = textFieldValue },
            hint = "What's happening?"
        )
    }
}

@Composable
private fun CloseButton() {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    IconButton(onClick = { appState.navigateTo(Screen.Home) }) {
        Icon(
            Icons.Filled.Clear,
            contentDescription = null,
            modifier = Modifier.size(50.dp),
            tint = theme.value!!.primary
        )
    }
}

@Composable
private fun TweetButton(tweetText: MutableState<TextFieldValue>) {
    val appState = viewModel<AppStateViewModel>()
    val theme = appState.theme.observeAsState(lightThemeColors)

    Button(
        onClick = {
            createNewTweet(tweetText.value.text)
            appState.navigateTo(Screen.Home)
        },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (tweetText.value.text.isEmpty()) Color(0xFFAAAAAA) else theme.value!!.primary
        )
    ) {
        Text(text = "Tweet", color = Color.White)
    }
}

@Composable
private fun TextFieldWithHint(
    value: TextFieldValue,
    modifier: Modifier,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(color = Color(0xFF666666), fontSize = 18.sp)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
    )
}
