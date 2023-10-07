package com.isaacdelosreyes.valorantforlogixs.core.data.model

import com.google.gson.annotations.SerializedName

data class AgentsDto(
    @SerializedName("data")
    val agents: List<AgentDto>?,
    val status: Int?
)