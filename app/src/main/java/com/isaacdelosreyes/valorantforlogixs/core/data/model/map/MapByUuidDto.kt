package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

import com.google.gson.annotations.SerializedName

data class MapByUuidDto(
    @SerializedName("data")
    val map: MapDto,
    val status: Int
)