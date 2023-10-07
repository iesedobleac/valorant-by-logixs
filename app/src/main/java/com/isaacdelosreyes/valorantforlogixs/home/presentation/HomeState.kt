package com.isaacdelosreyes.valorantforlogixs.home.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.Agent

data class HomeState(
    val agents: List<Agent> = emptyList()
)