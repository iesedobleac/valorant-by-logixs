package com.isaacdelosreyes.valorantforlogixs.mapdetail.presentation

import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.Map

data class MapDetailState(
    val map: Map? = null,
    val showErrorScreen: Boolean = false,
    val showLoaderComponent: Boolean = true,
)
