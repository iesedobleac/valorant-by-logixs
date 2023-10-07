package com.isaacdelosreyes.valorantforlogixs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isaacdelosreyes.valorantforlogixs.detail.presentation.DetailScreen
import com.isaacdelosreyes.valorantforlogixs.home.presentation.HomeScreen
import com.isaacdelosreyes.valorantforlogixs.utils.AGENT_UUID

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            HomeScreen {
                navController.navigate(
                    route = Routes.Detail.createRoute(it)
                )
            }
        }

        composable(
            route = "${Routes.Detail.route}/{${NavArgs.AgentUuid.key}}",
            arguments = listOf(navArgument(NavArgs.AgentUuid.key) {
                type = NavType.StringType
            })
        ) {
            DetailScreen()
        }
    }
}

enum class NavArgs(val key: String) {
    AgentUuid(AGENT_UUID)
}