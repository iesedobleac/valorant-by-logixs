package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

data class LocationDto(
    val x: Double?,
    val y: Double?
)

fun LocationDto.toDomain() =
    Location(
        x = x ?: 0.0,
        y = y ?: 0.0
    )