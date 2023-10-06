package com.isaacdelosreyes.valorantforlogixs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.isaacdelosreyes.valorantforlogixs.navigation.NavigationHost
import com.isaacdelosreyes.valorantforlogixs.ui.theme.ValorantForLogixsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantForLogixsTheme {
                NavigationHost()
            }
        }
    }
}