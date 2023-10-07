package com.isaacdelosreyes.valorantforlogixs.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val route: String
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        route = Routes.Home.route
    ),
    MAPS(
        selectedIcon = Icons.Default.Map,
        route = Routes.Maps.route
    )
}