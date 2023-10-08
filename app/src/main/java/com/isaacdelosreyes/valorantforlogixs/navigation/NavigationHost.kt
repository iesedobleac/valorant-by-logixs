package com.isaacdelosreyes.valorantforlogixs.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isaacdelosreyes.valorantforlogixs.agent.agentdetail.presentation.AgentDetailScreen
import com.isaacdelosreyes.valorantforlogixs.agent.agents.presentation.HomeScreen
import com.isaacdelosreyes.valorantforlogixs.launcher.presentation.LauncherScreen
import com.isaacdelosreyes.valorantforlogixs.map.mapdetail.presentation.MapDetailScreen
import com.isaacdelosreyes.valorantforlogixs.map.maps.presentation.MapsScreen
import com.isaacdelosreyes.valorantforlogixs.utils.AGENT_UUID
import com.isaacdelosreyes.valorantforlogixs.utils.MAP_UUID

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Launcher.route,
        modifier = modifier
    ) {
        composable(Routes.Launcher.route) {
            LauncherScreen {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Launcher.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(
            route = Routes.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            HomeScreen {
                navController.navigate(
                    route = Routes.AgentDetail.createRoute(it)
                )
            }
        }

        composable(
            route = "${Routes.AgentDetail.route}/{${NavArgs.AgentUuid.key}}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
            arguments = listOf(navArgument(NavArgs.AgentUuid.key) {
                type = NavType.StringType
            })
        ) {
            AgentDetailScreen {
                navController.popBackStack()
            }
        }

        composable(
            route = Routes.Maps.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            MapsScreen {
                navController.navigate(
                    route = Routes.MapDetail.createRoute(it)
                )
            }
        }

        composable(
            route = "${Routes.MapDetail.route}/{${NavArgs.MapUuid.key}}",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
            arguments = listOf(navArgument(NavArgs.MapUuid.key) {
                type = NavType.StringType
            })
        ) {
            MapDetailScreen {
                navController.popBackStack()
            }
        }
    }
}

enum class NavArgs(val key: String) {
    AgentUuid(AGENT_UUID),
    MapUuid(MAP_UUID)
}