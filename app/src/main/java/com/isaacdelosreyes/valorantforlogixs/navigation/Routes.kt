package com.isaacdelosreyes.valorantforlogixs.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")

    object AgentDetail: Routes("agent_detail") {
        fun createRoute(agentUuid: String) =
            "$route/$agentUuid"
    }

    object Maps: Routes("maps")

    object MapDetail: Routes("map_detail") {
        fun createRoute(mapUuid: String) =
            "$route/$mapUuid"
    }

}