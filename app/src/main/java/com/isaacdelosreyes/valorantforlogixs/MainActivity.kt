package com.isaacdelosreyes.valorantforlogixs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.isaacdelosreyes.valorantforlogixs.navigation.BottomNavigationBar
import com.isaacdelosreyes.valorantforlogixs.navigation.NavigationHost
import com.isaacdelosreyes.valorantforlogixs.navigation.TopLevelDestination
import com.isaacdelosreyes.valorantforlogixs.navigation.currentRoute
import com.isaacdelosreyes.valorantforlogixs.ui.theme.ValorantForLogixsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantForLogixsTheme {

                var navController = rememberNavController()

                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        items = TopLevelDestination.values().asList()
                    )
                }) {
                    NavigationHost(
                        navController = navController,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

