package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

data class Map(
    val assetPath: String,
    val callouts: List<Callout>,
    val coordinates: String,
    val displayIcon: String,
    val displayName: String,
    val listViewIcon: String,
    val mapUrl: String,
    val narrativeDescription: String,
    val splash: String,
    val tacticalDescription: String,
    val uuid: String,
    val xMultiplier: Double,
    val xScalarToAdd: Double,
    val yMultiplier: Double,
    val yScalarToAdd: Double
)