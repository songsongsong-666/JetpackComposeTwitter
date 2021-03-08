package com.sudo.rizwan.twitterclone.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun Home(scaffoldState: ScaffoldState = rememberScaffoldState() ) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { AppDrawer() },
        content = { Content(scaffoldState = scaffoldState) },
        floatingActionButton = { Fab() },
        bottomBar = { BottomBar() }
    )
}
