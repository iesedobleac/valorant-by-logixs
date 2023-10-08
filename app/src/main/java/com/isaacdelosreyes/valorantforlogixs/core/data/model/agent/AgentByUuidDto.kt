package com.isaacdelosreyes.valorantforlogixs.core.data.model.agent

import com.google.gson.annotations.SerializedName

data class AgentByUuidDto(
    @SerializedName("data")
    val agent: AgentDto,
    val status: Int
)