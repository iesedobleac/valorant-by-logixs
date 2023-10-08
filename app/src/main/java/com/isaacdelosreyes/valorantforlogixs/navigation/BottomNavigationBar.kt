package com.isaacdelosreyes.valorantforlogixs.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.isaacdelosreyes.valorantforlogixs.ui.theme.RadicalRed

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<TopLevelDestination>
) {

    val currentRoute = currentRoute(navController = navController)

    if (!showNavigationBar(currentRoute = currentRoute)) {

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            containerColor = Color.White,

            ) {
            items.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = screen.selectedIcon,
                            contentDescription = ""
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = RadicalRed,
                        selectedIconColor = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String {
    val currentDestination by navController.currentBackStackEntryAsState()
    return currentDestination?.destination?.route ?: Routes.Home.route
}

@Composable
fun showNavigationBar(currentRoute: String): Boolean {
    return currentRoute.contains(Routes.AgentDetail.route)
            || currentRoute.contains(Routes.MapDetail.route)
}