package com.sudo.rizwan.twitterclone

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val lightBackgroundColor = Color.White
private val darkBackgroundColor = Color(0xFF15202b)
private val darkerBackgroundColor = Color(0xFF0a0a0a)
private val primaryColor = Color(0xFF08a0e9)

val lightThemeColors = lightColors(
    primary = primaryColor,
    background = lightBackgroundColor,
    surface = lightBackgroundColor
)

val darkThemeColors = darkColors(
    primary = primaryColor,
    background = darkBackgroundColor,
    surface = darkBackgroundColor
)

val darkerThemeColors = darkColors(
    primary = primaryColor,
    background = darkerBackgroundColor,
    surface = darkerBackgroundColor
)