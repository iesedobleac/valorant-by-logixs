package com.isaacdelosreyes.valorantforlogixs.maps.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.Map

data class MapsState(
    val maps: List<Map> = emptyList()
)