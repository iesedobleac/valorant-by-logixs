package com.isaacdelosreyes.valorantforlogixs.detail.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.Ability
import com.isaacdelosreyes.valorantforlogixs.core.data.model.Agent

data class DetailState(
    val agent: Agent? = null,
    val selectedAbility: Ability? = null
)
