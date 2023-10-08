package com.isaacdelosreyes.valorantforlogixs.map.maps.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.Map

data class MapsState(
    val maps: List<Map> = emptyList(),
    val showErrorScreen: Boolean = false,
    val showLoaderComponent: Boolean = true,
)
