package com.isaacdelosreyes.valorantforlogixs.launcher.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isaacdelosreyes.valorantforlogixs.R
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import kotlinx.coroutines.delay

@Preview
@Composable
fun LauncherScreen(navigateToHome: () -> Unit = {}) {

    LaunchedEffect(key1 = Unit) {
        delay(3000)
        navigateToHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.launcher_image
            ),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )
    }
}