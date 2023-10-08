package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

import com.google.gson.annotations.SerializedName

data class MapsDto(
    @SerializedName("data")
    val maps: List<MapDto>,
    val status: Int
)