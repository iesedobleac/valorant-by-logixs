package com.isaacdelosreyes.valorantforlogixs.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")

    object Detail: Routes("detail") {
        fun createRoute(agentUuid: String) =
            "$route/$agentUuid"
    }

    object Maps: Routes("maps")

}