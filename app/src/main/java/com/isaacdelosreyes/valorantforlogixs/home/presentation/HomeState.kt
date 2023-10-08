package com.isaacdelosreyes.valorantforlogixs.home.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.Agent

data class HomeState(
    val agents: List<Agent> = emptyList(),
    val showErrorScreen: Boolean = false,
    val showLoaderComponent: Boolean = true,
)