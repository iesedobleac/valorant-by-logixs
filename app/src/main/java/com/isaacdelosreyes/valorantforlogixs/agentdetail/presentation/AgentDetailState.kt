package com.isaacdelosreyes.valorantforlogixs.agentdetail.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.Ability
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.Agent

data class AgentDetailState(
    val agent: Agent? = null,
    val selectedAbility: Ability? = null
)
